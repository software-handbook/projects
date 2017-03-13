package mks.dms.controller;

import javax.servlet.http.HttpSession;

import mks.dms.model.RegisterModel;
import mks.dms.service.AccountService;
import mks.dms.service.MailService;
import mks.dms.util.AppCons;
import mks.dms.util.AppCons.ERROR_CODE;
import mks.dms.util.RegisterAccountException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Processing register account.
 * @author ThachLN
 *
 */
@Controller
public class RegisterController extends BaseController {
    private static final Logger LOG = Logger.getLogger(RegisterController.class);
    
    @Autowired
    @Qualifier("registerValidator")
    private Validator registerValidator;
    
    @Autowired
    private MailService mailService;
    
    @Autowired
    private AccountService accountService;

    @Autowired
    @Qualifier("authenticationManager")
    protected AuthenticationManager authenticationManager;

    /**
     * This method is called when binding the HTTP parameter to bean (or model).
     * @param binder
     */
     @InitBinder
     protected void initBinder(WebDataBinder binder) {
         // Refer: http://www.coderanch.com/t/524168/Spring/SessionAttributes-Validator-set-InitBinder
         
         if ((registerValidator != null) && (binder.getTarget() != null) && (registerValidator.supports(binder.getTarget().getClass()))) {
             binder.setValidator(this.registerValidator);
         } else {
             LOG.warn("Could not set registerValidator");
         }
     }
     
    /**
    * Display screen to register account.
    * @param model
    * @return
    */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView goRegister(Model model) {
        ModelAndView mav = new ModelAndView("register");
        
        RegisterModel registerModel = new RegisterModel();

        mav.addObject(AppCons.MODEL, registerModel);

        return mav;
    }

    /**
    * Perform register account and auto login.
    * @param registerModel
    * @param bindingResult
    * @param session
    * @return
    */
    @RequestMapping(value = "doRegister", method = RequestMethod.POST)
    public ModelAndView doRegister(@ModelAttribute(AppCons.MODEL) @Validated RegisterModel registerModel, BindingResult bindingResult, HttpSession session) {
        ModelAndView mav = new ModelAndView("register");
        
        if (bindingResult.hasErrors()) {
            LOG.debug("Binding result; hasError=" + bindingResult.hasErrors());
            return mav;
        }
                
        String firstName = AppCons.NO_INPUT;
        String lastName = AppCons.NO_INPUT;
        String commonName = AppCons.NO_INPUT;
        String telNumber = AppCons.NO_INPUT_PHONE;
        
        try {
            accountService.registerManagerAccount(registerModel, firstName, lastName, commonName, telNumber);

            // mav.addObject(AppCons.SAVE_STATUS, AppCons.SUCCESS);
            // Auto authentication
            autoLogin(registerModel.getMail(), registerModel.getPassword(), session);
            
            return new ModelAndView("redirect:/" + registerModel.getCompany());
        } catch (RegisterAccountException raEx) {
            mav.addObject(AppCons.SAVE_STATUS, AppCons.FAIL);
            LOG.warn("Register account:" + registerModel.toString(), raEx);

            if (raEx.getErrorCode() == ERROR_CODE.REGISTER_GROUP) {
                mav.addObject(AppCons.MESSAGE_CODE, "Register_commpany_fail");
            } else if (raEx.getErrorCode() == ERROR_CODE.REGISTER_USER) {
                mav.addObject(AppCons.MESSAGE_CODE, "Register_user_fail");
            } else if (raEx.getErrorCode() == ERROR_CODE.EMAIL_SENDING) {
                mav.addObject(AppCons.MESSAGE_CODE, "Register_send_email_fail");
            } else {
                mav.addObject(AppCons.MESSAGE_CODE, "Unknow_error");
            }
        }
        
        return mav;
    }

    private void autoLogin(String username, String password, HttpSession session) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // this step is important, otherwise the new login is not in session which is required by Spring Security
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
    }
}
