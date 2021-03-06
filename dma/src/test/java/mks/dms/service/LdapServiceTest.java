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

import ldap.entry.GroupEntry;
import ldap.util.LdapConfiguration;
import ldap.util.LdapService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ThachLe
 *
 */
public class LdapServiceTest {
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
    public void testAddGroup() {
//        ApplicationContext appCtx = new ClassPathXmlApplicationContext("ldap-configuration.xml");
//        LdapConfiguration ldapConfiguration = appCtx.getBean("ldapConfiguration", LdapConfiguration.class);
//
//        LdapService ldapService = new LdapService(ldapConfiguration);
//        
//        GroupEntry groupEntry = new GroupEntry();
//        groupEntry.setGroupOU("NewGroup001");
//        groupEntry.setOuName("NewGroup001");
//        
//        ldapService.addGroup(groupEntry );
    }
}
