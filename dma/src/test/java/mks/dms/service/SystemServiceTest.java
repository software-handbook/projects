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

import static org.junit.Assert.assertEquals;

import java.util.List;

import mks.dms.dao.controller.ExParameterJpaController;
import mks.dms.dao.entity.Parameter;
import mks.dms.util.AppCons;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ThachLe
 *
 */
public class SystemServiceTest {
    private static final String DEFAULT_GROUP = "dma";
    /**
     * [Give the description for method].
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * [Give the description for method].
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link mks.dms.service.SystemService#initData(java.lang.String)}.
     */
    @Test
    public void testInitParam() {
//        SystemService systemService = new SystemService(DEFAULT_GROUP);
//        systemService.initParameterRank("admin");
//        
//        // Verify
//        ExParameterJpaController paramDaoCtrl = new ExParameterJpaController(BaseService.getEmf(DEFAULT_GROUP));
//        List<Parameter> listParam = paramDaoCtrl.findParameterByCd(AppCons.PARAM_RANK);
//        
//        assertEquals(3, listParam.size());
//        
//        assertEquals("A", listParam.get(0).getName());
        
    }

    @Test
    public void testInitParamEmail() {
//        SystemService systemService = new SystemService(DEFAULT_GROUP);
//        systemService.initParameterEmail("admin");
//        
//        // Verify
//        ExParameterJpaController paramDaoCtrl = new ExParameterJpaController(BaseService.getEmf(DEFAULT_GROUP));
//        List<Parameter> listParam = paramDaoCtrl.findParameterByCd(AppCons.PARAM_EMAIL);
//        
//        String value = paramDaoCtrl.findParameterByName(AppCons.PARAM_EMAIL, AppCons.PARAM_RESET_PASSWORD_SUBJECT, true);
//        
//        
//        assertEquals("[DMS] Khôi phục mật khẩu", value);
//        
//        value = paramDaoCtrl.findParameterByName(AppCons.PARAM_EMAIL, AppCons.PARAM_RESET_PASSWORD_FROM_ADDR, true);
//        assertEquals("service@mks.com.vn", value);
//        
//        value = paramDaoCtrl.findParameterByName(AppCons.PARAM_EMAIL, AppCons.PARAM_RESET_PASSWORD_FROM_NAME, true);
//        assertEquals("MeKong Solution Service", value);
//        
//        value = paramDaoCtrl.findParameterByName(AppCons.PARAM_EMAIL, AppCons.PARAM_RESET_PASSWORD_LINK, true);
//        assertEquals("http://tokutokuya.mks.com.vn/decisionmaker/confirm-reset-password", value);     
    }

}
