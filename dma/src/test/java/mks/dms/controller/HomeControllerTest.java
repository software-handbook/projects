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
package mks.dms.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ThachLe
 *
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/appServlet/servlet-context.xml"})
public class HomeControllerTest {

    @Autowired
    HomeController homeController;

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
     * Test method for {@link mks.dms.controller.HomeController#root(java.security.Principal, javax.servlet.http.HttpSession)}.
     */
    @Test
    public void testRootPrincipalHttpSession() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link mks.dms.controller.HomeController#root(java.lang.String, java.security.Principal, javax.servlet.http.HttpSession)}.
     */
    @Test
    public void testRootStringPrincipalHttpSession() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link mks.dms.controller.HomeController#home(org.springframework.ui.Model, java.security.Principal)}.
     */
    @Test
    public void testHome() {
        fail("Not yet implemented");
    }

}
