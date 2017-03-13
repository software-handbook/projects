package mks.dms.service;

import java.util.List;

import mks.dms.dao.controller.ExUserJpaController;
import mks.dms.dao.controller.UserJpaController;
import mks.dms.dao.entity.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @description Get data from database and map to model controller
 * @author truongtho88.nl
 */
@Service
public class UserControllerService extends BaseService {

    public UserControllerService() {
        super();
    }
    
	public UserControllerService(String groupName) {
        super(groupName);
    }

    // Logger to log information to console.
    private final static Logger LOG = Logger.getLogger(UserControllerService.class);
    
    /**
     * Get User By Cd.
     * @param cd
     * @return User
     */
    public User getUserById(int id) {
    	User user;
    	
    	UserJpaController daoCtrl = new UserJpaController(emf);
    	
    	user = daoCtrl.findUser(id);
    	
    	return user;
    }
    
    /**
     * Get User By Cd.
     * @param username
     * @return User
     */
    public User getUserByUsername(String username) {
    	User user;
    	
    	ExUserJpaController daoCtrl = new ExUserJpaController(emf);
    	
    	user = daoCtrl.findUserByUsername(username);
    	
    	return user;
    }
    
    /**
     * Get User By departmentCd.
     * @param departmentCd
     * @return List<User>
     */
    public List<User> getUserByDepartmentCd(String departmentCd) {
    	List<User> listUser;
    	
    	ExUserJpaController daoCtrl = new ExUserJpaController(emf);
    	
    	listUser = daoCtrl.getUserByDepartmentCd(departmentCd);
    	
    	return listUser;
    }
        
    /**
     * Get all of User of the groupId.
     * <br/>
     * @return list of User
     */
    public List<User> getAllUser() {
    	List<User> listUsers;
    	
    	ExUserJpaController userDaoCtrl = new ExUserJpaController(emf, this.groupId);
    	
    	listUsers = userDaoCtrl.findUserEntitiesInGroup();
    	
    	return listUsers;
    }
}
