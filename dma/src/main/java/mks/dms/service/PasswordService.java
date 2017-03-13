package mks.dms.service;

import java.util.List;

import mks.dms.dao.controller.ExResetPasswordJpaController;
import mks.dms.dao.entity.ResetPassword;
import mks.dms.util.AppCons;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class PasswordService extends BaseService {
    /* Logger to log information */
    private final static Logger LOG = Logger.getLogger(PasswordService.class);

    private ExResetPasswordJpaController controller = null;

    public PasswordService() {
        super();
        controller = new ExResetPasswordJpaController(super.masterEmf);
    }

    public PasswordService(String groupName) {
        super(groupName);
        controller = new ExResetPasswordJpaController(super.emf);
    }

    public List<ResetPassword> getParameterByCd(String cd) {
        List<ResetPassword> listParameter = controller.findParameterByCd(cd);

        return listParameter;
    }

    /**
     * [Give the description for method].
     * 
     * @param dateKey
     * @param randomKey
     * @return
     */
    public boolean checkKey(String email, String dateKey, String randomKey) {
        ResetPassword resetPasswdRec = controller.findParameterByDescription(AppCons.RESET_PASSWORD, email, dateKey,
                randomKey);

        return (resetPasswdRec != null);
    }
}
