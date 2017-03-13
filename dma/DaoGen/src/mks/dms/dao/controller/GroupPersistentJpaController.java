/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mks.dms.dao.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.GroupPersistent;

/**
 *
 * @author ThachLe
 */
public class GroupPersistentJpaController implements Serializable {

    public GroupPersistentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GroupPersistent groupPersistent) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(groupPersistent);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GroupPersistent groupPersistent) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            groupPersistent = em.merge(groupPersistent);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = groupPersistent.getId();
                if (findGroupPersistent(id) == null) {
                    throw new NonexistentEntityException("The groupPersistent with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GroupPersistent groupPersistent;
            try {
                groupPersistent = em.getReference(GroupPersistent.class, id);
                groupPersistent.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The groupPersistent with id " + id + " no longer exists.", enfe);
            }
            em.remove(groupPersistent);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GroupPersistent> findGroupPersistentEntities() {
        return findGroupPersistentEntities(true, -1, -1);
    }

    public List<GroupPersistent> findGroupPersistentEntities(int maxResults, int firstResult) {
        return findGroupPersistentEntities(false, maxResults, firstResult);
    }

    private List<GroupPersistent> findGroupPersistentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GroupPersistent.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public GroupPersistent findGroupPersistent(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GroupPersistent.class, id);
        } finally {
            em.close();
        }
    }

    public int getGroupPersistentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GroupPersistent> rt = cq.from(GroupPersistent.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
