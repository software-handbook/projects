package mks.dms.service;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import ldap.entry.Entry;
import ldap.entry.GroupEntry;
import ldap.entry.UserEntry;
import ldap.util.LdapConfiguration;
import ldap.util.LdapService;
import mks.dms.dao.controller.ExUserJpaController;
import mks.dms.dao.entity.User;
import mks.dms.model.RegisterModel;
import mks.dms.util.AppCons;
import mks.dms.util.AppCons.ERROR_CODE;
import mks.dms.util.RegisterAccountException;
import openones.mail.MailServerInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AccountService extends BaseService {
	private static Logger LOG = Logger.getLogger(AccountService.class);
	
	/** This configuration is used for get ldap-configuration.xml. */
	@Autowired
	LdapConfiguration ldapConfiguration;
	
	@Autowired
    private MailServerInfo mailServerInfo;

    public AccountService() {
        super();
    }
    
    public AccountService(LdapConfiguration ldapConfiguration) {
        this.ldapConfiguration = ldapConfiguration;
    }

    public AccountService(String groupName) {
        super(groupName);
    }

    /**
    * [Give the description for method].
    * <br/>
    * Transaction:
    * - Send email to welcome the new user.
    * - Add a record of user into database.
    * @param company the group name has not existed in LDAP yet.
    * @param userEntry
    * @throws RegisterAccountException error while add group or user into LDAP.
    */

    public void registerManagerAccount(RegisterModel registerModel, String firstName, String lastName,
            String commonName, String telNumber) throws RegisterAccountException {
        LdapService ldapService = new LdapService(ldapConfiguration);
        
        GroupEntry groupEntry = new GroupEntry(registerModel.getCompany(), ldapConfiguration.getRootGroupOU());
        
        boolean addGroupOK = ldapService.addGroup(groupEntry);

        if (addGroupOK) {
            String groupOU = groupEntry.getDn();
            
            LOG.info("Created group '" + groupEntry.getDn() + "' success.");
            
            UserEntry userEntry = new UserEntry(registerModel.getMail(), groupOU, firstName, lastName,
                    registerModel.getMail(), commonName, telNumber, registerModel.getPassword());
            
            boolean addUserOK = ldapService.addUser(userEntry);
            
            if (addUserOK) {
                LOG.info("Created acount '" + registerModel.toString() + "' success.");
                
                // Store account into database and send email welcome to user.
                MailService mailService = new MailService(mailServerInfo);
                try {
                    // Step 1: Send email to welcome.
                    mailService.sendMailWelcome(registerModel.getMail(), registerModel.getMail());
                    
                    // Step 2: Add user into database
                    ExUserJpaController userJpaCtrl = new ExUserJpaController(BaseService.getEmf(this.groupId));
                    User user = new User();
                    user.setUsername(registerModel.getMail());
                    user.setEmail(registerModel.getMail());
                    user.setEnabled(true);
                    user.setFirstname(AppCons.NO_INPUT);
                    user.setLastname(AppCons.NO_INPUT);
                    user.setCreated(new Date());
                    user.setCreatedbyUsername(AppCons.NO_INPUT);
                    user.setGroupId(registerModel.getCompany());
                    
                    userJpaCtrl.create(user);
                    
                } catch (MessagingException mEx) {
                    LOG.warn("Could not send welcome email to '" + registerModel.getMail(), mEx);
                    // Rollback: delete user, delete group
                    ldapService.deleteUser(userEntry.getDn());
                    
                    LOG.info("Rollback: Deleting user '" + userEntry.getDn() + "'");
                    
                    boolean delGroupOK = ldapService.deleteGroup(groupEntry.getDn());
                    
                    LOG.info("Deleting group '" + groupEntry.getDn() + "' " + delGroupOK);

                    throw new RegisterAccountException(ERROR_CODE.EMAIL_SENDING);                    
                }
            } else {
                // Roll back: delete the added group
                LOG.info("Rollback created group '" + groupEntry.getDn() + "'");
                boolean delGroupOK = ldapService.deleteGroup(groupEntry.getDn());
                
                LOG.info("Rollback: Deleting group '" + groupEntry.getDn() + "' " + delGroupOK);

                throw new RegisterAccountException(ERROR_CODE.REGISTER_USER);
            }
        } else {
            throw new RegisterAccountException(ERROR_CODE.REGISTER_GROUP); 
        }
    }

    public Entry findUserByUid(String username) {
        LdapService ldapService = new LdapService(ldapConfiguration);
        
        return ldapService.findUserByUid(username);
    }

    public boolean checkPassword(String username, String password) {
        LdapService ldapService = new LdapService(ldapConfiguration);
        
        return ldapService.checkPassword(username, password);
    }

    public boolean changePass(String userDN, String newpwd) {
        LdapService ldapService = new LdapService(ldapConfiguration);
        
        return ldapService.changePass(userDN, newpwd);
    }

    public UserEntry findUserByEmail(String email) {
        LdapService ldapService = new LdapService(ldapConfiguration);
        
        return ldapService.findUserByEmail(email);
    }

    public List<Entry> findUsersByDN(String groupDn) {
        LdapService ldapService = new LdapService(ldapConfiguration);
        
        return ldapService.findUsersByDN(groupDn);
    }

    public String getJsonLdap(String ou) {
        LdapService ldapService = new LdapService(ldapConfiguration);
        
        return ldapService.getJsonLdap(ou);
    }

    public boolean createUserAuto(User logonUser) {
        boolean isCreated;
        ExUserJpaController userJpaCtrl = new ExUserJpaController(getEmf());
        User findUser = userJpaCtrl.findUserByUsername(logonUser.getUsername());
        
        if (findUser == null) {
            // Update system information
            logonUser.setCreated(new Date());
            logonUser.setCreatedbyUsername(AppCons.SYSTEM);
            userJpaCtrl.create(logonUser);
            isCreated = true;
        } else {
            isCreated = false;
        }
        
        return isCreated;
    }

    /**
    * [Give the description for method].
    * @param logonUser input and output
    * @return true if load information from LDAP successfully.
    * Updated information:
    * - First name
    * - Last name
    * - Email
    */
    public boolean loadUserInfo(User logonUser) {
        LdapService ldapService = new LdapService(ldapConfiguration);
        UserEntry ldapUser = (UserEntry) ldapService.findUserByUid(logonUser.getUsername());
        
        if (ldapUser != null) {
            logonUser.setFirstname(ldapUser.getFirstName());
            logonUser.setLastname(ldapUser.getLastName());
            logonUser.setEmail(ldapUser.getEmail());
            
            return true;
        } else {
            return false;
        }
        
    }

}
