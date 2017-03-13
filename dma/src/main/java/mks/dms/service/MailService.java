package mks.dms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;

import ldap.entry.UserEntry;
import mks.dms.dao.controller.ExParameterJpaController;
import mks.dms.dao.entity.ResetPassword;
import mks.dms.util.AppCons;
import openones.mail.MailInfo;
import openones.mail.MailServerInfo;
import openones.mail.MailUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocky.common.CommonUtil;
import rocky.common.Constant;
import rocky.common.FileUtil;

import com.novell.ldap.util.Base64;

@Service
public class MailService extends BaseService {

    private static final String TEMPLATE_PATH = "/html-mail-template.html";
	private static Logger LOG = Logger.getLogger(MailService.class);
	
	@Autowired
	private MailServerInfo mailServerInfo;

    public MailService() {
        super();
    }

    public MailService(String groupName) {
        super(groupName);
    }

//	@Autowired
	public MailService(MailServerInfo mailServerInfo) {
	    this.mailServerInfo = mailServerInfo;
	}
	

	/**
	* Step 1: Create a record of reset link into database
	* Step 2: Send email to the user 
	* @param user
	* @return
	*/
	public boolean sendMailReset(UserEntry user) {
        boolean result = false;
        String content = FileUtil.getContent(TEMPLATE_PATH, true, Constant.DEF_ENCODE);
        MailInfo mail = new MailInfo();
        // variable path
        Map<String, Object> varMap = new HashMap<String, Object>();
        varMap.put("uid", user.getUid());
        varMap.put("to", user.getEmail());
        varMap.put("password", user.getPassword());
        varMap.put("from", getResetEmailFromName());

        EntityManager em = this.emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Save parameter
            /*
             * CD = "ResetPasswd" NAME = account VALUE = yyyy-MM-dd HH:mm:ss
             */
            ResetPassword resetPasswdLog = new ResetPassword();
            resetPasswdLog.setCd(AppCons.RESET_PASSWORD);
            resetPasswdLog.setName(user.getEmail());

            resetPasswdLog.setGroupId(user.getGroup());
            Date curDate = new Date();
            String strDate = CommonUtil.formatDate(curDate, AppCons.DATETIME_FORMAT);
            resetPasswdLog.setValue(strDate);

            resetPasswdLog.setCreated(curDate);
            resetPasswdLog.setCreatedbyUsername(user.getUid());
            resetPasswdLog.setEnabled(true);
            
            // Set Random key
            Random random = new Random();
            resetPasswdLog.setDescription(String.valueOf(random.nextLong()));

            String encodeValue = encode(resetPasswdLog.getValue());

            // Key 1: current date
            // Key 2:
            String randomNumber = encode(resetPasswdLog.getDescription());
            String resetPasswordLink = getResetPasswordLink() + "?email=" + user.getEmail() + "&key1=" + encodeValue
                    + "&key2=" + randomNumber;
            
            // Step 1: Save the parameter into database
            em.persist(resetPasswdLog);
            
            
            varMap.put("resetPasswordLink", resetPasswordLink);

            // Content after replaced values of variables
            LOG.debug("content before replace=: " + content);
            content = CommonUtil.formatPattern(content, varMap);
            LOG.debug("content after replace=: " + content);

            ArrayList<String> list = new ArrayList<String>();
            list.add(user.getEmail());
            mail.setMailTo(list);
            
            mail.setMailFrom(getResetEmailFromAddress());
            
            mail.setMailSubject(getResetEmailSubject());

            mail.setMailBody(content);
            
            // Step 2: Email sending
            MailUtil.sendSimpleHtmlEmail(mailServerInfo, mail);

            em.getTransaction().commit();
            
            result = true;

        } catch (MessagingException ex) {
            LOG.error("Error!. Can't send", ex);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

		return result;
	}

    /**
    * [Give the description for method].
    * @param userId
    * @param email
    * @throws MessagingException
    */
    public void sendMailWelcome(String userId, String email) throws MessagingException {
        String subject = getTemplateEmailWelcomeTitle();
        String content = getTemplateEmailWelcomeContent();

        MailInfo mail = new MailInfo();
        // prepare to replace variables
        Map<String, Object> varMap = new HashMap<String, Object>();
        varMap.put("uid", userId);

        // Content after replaced values of variables
        LOG.debug("content before replace=: " + content);
        content = CommonUtil.formatPattern(content, varMap);
        LOG.debug("content after replace=: " + content);

        ArrayList<String> list = new ArrayList<String>();
        list.add(email);
        mail.setMailTo(list);

        mail.setMailFrom(getResetEmailFromAddress());

        mail.setMailSubject(subject);

        mail.setMailBody(content);

        // Step 2: Email sending
        MailUtil.sendSimpleHtmlEmail(mailServerInfo, mail);

    }
	   
    /**
    * Encode value of parameter by Base64 encoder.
    * @param param
    * @return
    */
    private String encode(String text) {
        return Base64.encode(text);
    }

    private String getResetPasswordLink() {
        String resetPasswordLink;
        ExParameterJpaController paramDaoCtrl = new ExParameterJpaController(this.emf);
        
        boolean isEnable = true;
        resetPasswordLink = paramDaoCtrl.findParameterByName(AppCons.PARAM_EMAIL, AppCons.PARAM_RESET_PASSWORD_LINK, isEnable);
        
        return resetPasswordLink;
    }
    
    private String getResetEmailSubject() {
        String resetEmailSubject;
        ExParameterJpaController paramDaoCtrl = new ExParameterJpaController(this.emf);
        
        boolean isEnable = true;
        resetEmailSubject = paramDaoCtrl.findParameterByName(AppCons.PARAM_EMAIL, AppCons.PARAM_RESET_PASSWORD_SUBJECT, isEnable);
        
        return resetEmailSubject;
    }
    

    private String getResetEmailFromAddress() {
        String resetPasswordFromName;
        ExParameterJpaController paramDaoCtrl = new ExParameterJpaController(this.emf);
        
        boolean isEnable = true;
        resetPasswordFromName = paramDaoCtrl.findParameterByName(AppCons.PARAM_EMAIL, AppCons.PARAM_RESET_PASSWORD_FROM_ADDR, isEnable);
        
        return resetPasswordFromName;
    }
    
    private String getResetEmailFromName() {
        String resetPasswordFromName;
        ExParameterJpaController paramDaoCtrl = new ExParameterJpaController(this.emf);
        
        boolean isEnable = true;
        resetPasswordFromName = paramDaoCtrl.findParameterByName(AppCons.PARAM_EMAIL, AppCons.PARAM_RESET_PASSWORD_FROM_NAME, isEnable);
        
        return resetPasswordFromName;
    }

    private String getTemplateEmailWelcomeContent() {
        ExParameterJpaController paramDaoCtrl = new ExParameterJpaController(this.emf);
        
        boolean isEnable = true;

        return paramDaoCtrl.findParameterByName(AppCons.PARAM_EMAIL, AppCons.PARAM_TEMPLATE_WELCOME_CONTENT, isEnable);
    }
    
    private String getTemplateEmailWelcomeTitle() {
        ExParameterJpaController paramDaoCtrl = new ExParameterJpaController(this.emf);
        
        boolean isEnable = true;

        return paramDaoCtrl.findParameterByName(AppCons.PARAM_EMAIL, AppCons.PARAM_TEMPLATE_WELCOME_TITLE, isEnable);
    }
}
