package mks.dms.dao.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.ResetPassword;

import org.apache.log4j.Logger;

public class ExResetPasswordJpaController extends ResetPasswordJpaController {
    /** Logging. */
    private final static Logger LOG = Logger.getLogger(ExResetPasswordJpaController.class);
    
	public ExResetPasswordJpaController(EntityManagerFactory emf) {
		super(emf);
	}
	
	public List<ResetPassword> findParameterByCd(String cd) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
        Root<ResetPassword> rt = cq.from(ResetPassword.class);
        cq.where(cb.equal(rt.get("cd"), cd));
        Query query = em.createQuery(cq);
        List<ResetPassword> result = query.getResultList();
        
        return result;
	}

    public String findParameterByName(String cd, String name, boolean isEnable) {
        List<Request> lstRequest;
        EntityManager em = getEntityManager();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();

            Root<ResetPassword> root = cq.from(ResetPassword.class);
            cq.select(root.get("value"));
            
            // Build where condition
            Predicate predicate = cb.equal(root.get("cd"), cd);
            predicate = cb.and(predicate, cb.equal(root.get("name"), name));
            predicate = cb.and(predicate, cb.equal(root.get("enabled"), isEnable));


            cq.where(predicate);

            Query query = em.createQuery(cq);

            return (String) query.getSingleResult();

        } catch (NoResultException nrEx) {
            LOG.warn("Could not found parameter cd=" + cd + ";name=" + name + ";enabled=" + isEnable, nrEx);
        } finally {
            em.close();
        }

        return null;
    }

    public ResetPassword findParameterByDescription(String cd, String name, String value, String description) {
        List<Request> lstRequest;
        EntityManager em = getEntityManager();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();

            Root<ResetPassword> root = cq.from(ResetPassword.class);
            
            // Build where condition
            Predicate predicate = cb.equal(root.get("cd"), cd);
            predicate = cb.and(predicate, cb.equal(root.get("name"), name));
            predicate = cb.and(predicate, cb.equal(root.get("value"), value));
            predicate = cb.and(predicate, cb.equal(root.get("description"), description));
            predicate = cb.and(predicate, cb.equal(root.get("enabled"), true));


            cq.where(predicate);

            Query query = em.createQuery(cq);

            return (ResetPassword) query.getSingleResult();

        } catch (NoResultException nrEx) {
            LOG.warn("Could not found parameter cd=" + cd + ";name=" + name + ";value=" + value + ";description=" + description, nrEx);
        } finally {
            em.close();
        }

        return null;
    }
}
