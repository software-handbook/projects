package mks.dms.service;

import java.util.List;

import mks.dms.dao.controller.ExParameterJpaController;
import mks.dms.dao.entity.Parameter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ParameterService extends BaseService {
    /* Logger to log information */
	private final static Logger LOG = Logger.getLogger(ParameterService.class);
	
	private ExParameterJpaController controller = null;

   public ParameterService() {
        super();
        controller = new ExParameterJpaController(super.masterEmf);
    }
	   
	public ParameterService(String groupName) {
        super(groupName);
        controller = new ExParameterJpaController(super.emf);
    }
	
	public List<Parameter> getParameterByCd(String cd) {
		List<Parameter> listParameter = controller.findParameterByCd(cd);
		return listParameter;
	}
}
