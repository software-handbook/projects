package mks.dms.controller;

import java.security.Principal;
import java.util.List;

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
public class RuleController extends BaseController {
	/**  */
	private static final Logger LOG = Logger.getLogger(RuleController.class);
	
	private final RequestService requestService;
	
	@Autowired
    public RuleController(RequestService requestService) {
        this.requestService = requestService;
    }


    @RequestMapping(value="{groupName}/listRule", method = RequestMethod.GET)
    public ModelAndView listRule(Model model, Principal principal){
        ModelAndView mav = new ModelAndView("listRule");
        
        String groupName = getGroup(principal);
        
        List<Request> lstRule = requestService.findRule(groupName);
        
        mav.addObject("lstRule", lstRule);
        mav.addObject(AppCons.SELECTED_MENU, "listRule");
        return mav;
    }
    
    @RequestMapping(value="{groupName}/viewRule", method = RequestMethod.GET)
    public ModelAndView viewRule(@RequestParam("id") Integer requestId) {
        ModelAndView mav = new ModelAndView("viewRule");
        
        LOG.debug("requestId=" + requestId);
        Request rule = requestService.getDaoController().findRequest(requestId);
        
        mav.addObject("rule", rule);
        
        return mav;
    }
}
