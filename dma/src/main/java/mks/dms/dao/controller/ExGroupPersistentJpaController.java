/**
 * Licensed to Open-Ones under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package mks.dms.dao.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import mks.dms.dao.entity.GroupPersistent;

import org.apache.log4j.Logger;

/**
 * This class extends interfaces of UserJpaController. <br/>
 * 
 * @author ThachLe
 */
public class ExGroupPersistentJpaController extends GroupPersistentJpaController {
    /** Logging. */
    private final static Logger LOG = Logger.getLogger(ExGroupPersistentJpaController.class);
        
    public ExGroupPersistentJpaController(EntityManagerFactory emf) {
        super(emf);
    }

    public GroupPersistent findGroupPersistentByGroupId(String groupId) {
        GroupPersistent groupPersistent = null;
        EntityManager em = getEntityManager();

        try {
            
            Query query = em.createNamedQuery("GroupPersistent.findByGroupId");
            query.setParameter("groupId", groupId);
            groupPersistent = (GroupPersistent) query.getSingleResult();
            
        } catch (NoResultException nsEx) { 
            // Do nothing
        }

        return groupPersistent;        
    }
}
