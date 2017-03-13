package mks.dms.controller;

import java.security.Principal;
import java.util.List;

import mks.dms.dao.controller.ExRequestJpaController;
import mks.dms.dao.entity.Request;
import mks.dms.service.RequestService;
import mks.dms.util.AppCons;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ThachLe
 *
 */
@Controller
public class AnnouncementController extends BaseController {
	/**  */
	private static final Logger LOG = Logger.getLogger(AnnouncementController.class);
	
	private final RequestService requestService;
	
	@Autowired
    public AnnouncementController(RequestService requestService) {
        this.requestService = requestService;
    }


    @RequestMapping(value="{groupName}/listAnnouncement", method = RequestMethod.GET)
    public ModelAndView listAnnouncement(Model model, Principal principal){
        ModelAndView mav = new ModelAndView("listAnnouncement");
        
        String groupName = getGroup(principal);

        List<Request> lstAnnouncement = requestService.findAnnouncement(groupName);
        
        mav.addObject("lstAnnouncement", lstAnnouncement);
        mav.addObject(AppCons.SELECTED_MENU, "listAnnouncement");
        return mav;
    }
    
    @RequestMapping(value="{groupName}/viewAnnouncement", method = RequestMethod.GET)
    public ModelAndView viewAnnouncement(@RequestParam("id") Integer requestId) {
        ModelAndView mav = new ModelAndView("viewAnnouncement");
        
        LOG.debug("requestId=" + requestId);
        Request announcement = requestService.getDaoController().findRequest(requestId);
        
        mav.addObject("announcement", announcement);
        
        return mav;
    }
    
}
