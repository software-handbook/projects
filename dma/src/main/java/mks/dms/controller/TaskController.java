package mks.dms.controller;

import java.security.Principal;
import java.util.List;

import mks.dms.dao.entity.Request;
import mks.dms.model.SearchTaskConditionModel;
import mks.dms.service.RequestService;
import mks.dms.util.AppCons;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ThachLe
 *
 */
@Controller
public class TaskController {
	/**  */
	private static final Logger LOG = Logger.getLogger(TaskController.class);
	
    private final RequestService requestService;

    @Autowired
    public TaskController(RequestService requestService) {
        this.requestService = requestService;
    }

    /**
     * Show myListTask page
     **/
    @RequestMapping(value="{groupName}/myListTask")
    public ModelAndView showMyListTask(@ModelAttribute(AppCons.MODEL) SearchTaskConditionModel searchConditionModel, Principal principal) {
        ModelAndView mav = new ModelAndView("myListTask");
        
        searchConditionModel.setUsername(principal.getName());
        List<Request> listRequest = requestService.findTaskOfUser(searchConditionModel);
//        List<Request> listRequest1 = requestService.getDaoController().getListRequestByCreatedbyUsername(principal.getName());
//        List<Request> listRequest2 = requestService.getDaoController().getListRequestByAssigneeUsername(principal.getName());
//        List<Request> listRequest3 = requestService.g@ModelAttribute(MODEL) SearchRequestConditionModel searchConditionModel, Principal principaletDaoController().getListRequestByManagerUsername(principal.getName());
//        
//        listRequest1.removeAll(listRequest2);
//        listRequest1.addAll(listRequest2);
//        listRequest1.removeAll(listRequest3);
//        listRequest1.addAll(listRequest3);
        
        mav.addObject("requests", listRequest);
        mav.addObject(AppCons.SELECTED_MENU, "myListTask");
        
        return mav;
    }
}
