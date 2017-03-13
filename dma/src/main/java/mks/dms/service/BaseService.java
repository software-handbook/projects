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
package mks.dms.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mks.dms.dao.controller.ExGroupPersistentJpaController;
import mks.dms.dao.entity.GroupPersistent;
import mks.dms.dao.entity.User;

import org.apache.log4j.Logger;
import org.eclipse.persistence.config.PersistenceUnitProperties;

/**
 * @author ThachLe
 *
 */
public class BaseService {
    private final static Logger LOG = Logger.getLogger(BaseService.class);

    /** Refer in tag "persistence-unit" in configuration file persistent.xml. */
    final static String DEFAULT_PU = "DMA-PU";

    /** Load database connection from configuration file persistent.xml. */
    public static EntityManagerFactory masterEmf = Persistence.createEntityManagerFactory(DEFAULT_PU);
    
    
    EntityManagerFactory emf = null;
    String groupId = null;
    User user;

    /** <groupName, EntityManagerFactory>.*/
    static Map<String, EntityManagerFactory> mapEmf = new HashMap<String, EntityManagerFactory>();
        
    public BaseService() {
        emf = masterEmf;
    }
    
    public BaseService(String groupId) {
        this.groupId = groupId;
        emf = getEmf(groupId);
    }
    
    /**
    * Get default EntityManagerFactory.
    * @return
    */
    public static EntityManagerFactory getEmf() {
        return masterEmf;
    }

    public static EntityManagerFactory getEmf(String groupId) {
        EntityManagerFactory existedEmf = null;
        // Get database connection from database master
        ExGroupPersistentJpaController gpJpaCtrl = new ExGroupPersistentJpaController(masterEmf);
        GroupPersistent groupPersistent = gpJpaCtrl.findGroupPersistentByGroupId(groupId);
        
        if (groupPersistent != null) {
            //
            existedEmf = mapEmf.get(groupId);
            if (existedEmf == null) {
                // EMF has no existed yet
                // Create a new one
                Map props = new HashMap<String, String>();
                props.put(PersistenceUnitProperties.JDBC_DRIVER, groupPersistent.getJdbcDriver());
                props.put(PersistenceUnitProperties.JDBC_URL, groupPersistent.getJdbcUrl());
                props.put(PersistenceUnitProperties.JDBC_USER, groupPersistent.getJdbcUsername());
                props.put(PersistenceUnitProperties.JDBC_PASSWORD, groupPersistent.getJdbcPassword());
                props.put(PersistenceUnitProperties.DDL_GENERATION, "create-tables");

                existedEmf = Persistence.createEntityManagerFactory(DEFAULT_PU, props);
                
                // Store new EMF into map
                mapEmf.put(groupId, existedEmf);
                LOG.info("Add more an EntityManagerFactory. New size of the map:" + mapEmf.size());
            } else {
                // Do nothing
            }
            
        } else {
            LOG.warn("Could not found group configuration of '" + groupId + "'");
            existedEmf = masterEmf;
        }

        return existedEmf;
    }

    /**
     * Get value of user.
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value for user.
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get value of groupId.
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Set the value for groupId.
     * @param groupId the groupId to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
