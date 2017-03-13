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

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.RequestType;
import mks.dms.service.BaseService;
import mks.dms.service.MasterService;

import org.junit.Test;

/**
 * @author ThachLN
 *
 */
public class ExRequestTypeJpaControllerTest {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("DecisionMaker-DBModelPU");


    @Test
    public void testDeleteAttachment() throws Exception {
//        ExRequestJpaController exDaoCtrl = new ExRequestJpaController(emf);
//        
//        int requestId = 3;
//        int fileId = 1;
//        exDaoCtrl.deleteAttachment(requestId, fileId);
//        
//        Request request = exDaoCtrl.findRequest(requestId);
//        assertNull(request.getFilename1());
//        assertNull(request.getAttachment1());
    }

}
