package mks.dms.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.User;
import mks.dms.model.DurationUnit;
import mks.dms.service.AccountService;
import mks.dms.service.MasterService;
import mks.dms.service.UserControllerService;
import mks.dms.util.AppCons;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 * <br/>
 * Datum are stored in session:<br/>
 * listRequestType: Types of request
 * listUser: List of users [TODO] Change to ajax request in next version
 * listDurationUnit: List of duration unit
 */
@Controller
@SessionAttributes({"listRequestType","listUser", "listDurationUnit", "listDepartment", "listStatus", "_KEY_GROUP"})
public class HomeController extends BaseController {
    /** Logging. */
    private final static Logger LOG = Logger.getLogger(HomeController.class);

    private final String ADMIN_USER = "admin";

    @Autowired
    private AccountService accountService;
    /**
     * Entry point of the application.
     * @param principal
     * @param session
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(Principal principal, HttpSession session) {
        if (principal == null) {
            return "login";
        } else {
            this.groupId = (String) session.getAttribute(KEY_GROUP);

            if (groupId == null) {
                groupId = super.getGroup(principal);

                if (groupId != null) {
                    session.setAttribute(KEY_GROUP, groupId);
                } else {
                    groupId = DEFAULT_GROUP;
                }
            } else {
                // Do nothing
            }
        }
        return "redirect:" + groupId + "/home";
    }
    
    @RequestMapping(value = "/{groupName}", method = RequestMethod.GET)
    public String root(@PathVariable("") String groupName, Principal principal, HttpSession session) {
        String group = (String ) session.getAttribute(KEY_GROUP);
        
        if (principal == null) {
            return "login";
        } else {
            if (group == null) {
                group = super.getGroup(principal);
                session.setAttribute(KEY_GROUP, group);
            } else {
                // Do nothing
            }

            return "redirect:" + group + "/home";
//            if (group.equalsIgnoreCase(groupName)) {
//                return "redirect:" + group + "/home";
//            } else {
//                LOG.error("Group in URL '" + groupName + "' invalid.");
//                return "redirect:/logout";
//            }
        }
        //return home(model, principal);
    }
    
	@RequestMapping(value = "/{groupName}/home", method = RequestMethod.GET)
	public ModelAndView home(Model model, Principal principal) {
	    ModelAndView mav;
	    
	    if (principal == null) {
	        mav = new ModelAndView("login");
        } else {
            LOG.debug("User=" + principal.getName());

            if (ADMIN_USER.equalsIgnoreCase(principal.getName())) {
                mav = new ModelAndView("home-admin");
            } else {
                mav = new ModelAndView("home");
            }
            
            // In case authenticated by LDAP, the user is not existed on database,
            // Automatically create user.
            
            // Get username, groupId
            User logonUser = parsePrincipal(principal);
            accountService.loadUserInfo(logonUser);

            accountService.createUserAuto(logonUser);

            shareCommonDataSession(mav);
            mav.addObject(AppCons.SELECTED_MENU, "home");
        }
	    return mav;
	}
	
	/**
	* Put all common data into the session.
	* <br/>
	* lstRequestTypes: List of request types
	* listUser: List of Users
	* listDurationUnit: List of Duration Units
	* @param mav
	*/
	private void shareCommonDataSession(ModelAndView mav) {
	    MasterService masterService = new MasterService(this.groupId);
        List<RequestType> lstRequestTypes = masterService.getRequestTypes();
        LOG.debug("lstRequestTypes=" + lstRequestTypes);
        mav.addObject("listRequestType", lstRequestTypes);
        
        UserControllerService userService = new UserControllerService(this.groupId); 
        List<User> listUser = userService.getAllUser();
        mav.addObject("listUser", listUser);
        
        List<DurationUnit> listDurationUnit = MasterService.getDurationUnits();
        mav.addObject("listDurationUnit", listDurationUnit);
        
        List<Department> listDepartment = masterService.getDepartments();
        mav.addObject("listDepartment", listDepartment);
        
        List<String> listStatus = masterService.getAllStatus();
        mav.addObject("listStatus", listStatus);
	}
}
