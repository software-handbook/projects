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
package mks.dms.model.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ldap.entry.Entry;
import ldap.util.LdapConfiguration;
import ldap.util.LdapService;
import mks.dms.dao.controller.ExUserJpaController;
import mks.dms.dao.entity.User;
import mks.dms.model.RegisterModel;
import mks.dms.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import rocky.common.CHARA;
import rocky.common.CommonUtil;

/**
 * @author ThachLN
 */
public class RegisterModelValidator implements Validator {

    private static final String MSGCD_V001 = "Mail_existed";
    private static final String FIELD_COMPANY = "company";
    private static final String FIELD_MAIL = "mail";
    private static final int MIN_COMPANY_NAME = 6;
    private static final int MIN_PASSWORD = 6;
    private static final String EMAIL_PATTERN = 
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /** This configuration is used for get ldap-configuration.xml. */
    @Autowired
    LdapConfiguration ldapConfiguration;
    
    @Override
    public boolean supports(Class<?> paramClass) {
        return RegisterModel.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        // Check required
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_MAIL, "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "retypePassword", "required");

        RegisterModel registerModel = (RegisterModel) obj;
        String mail = registerModel.getMail();
        String password = registerModel.getPassword();
        String retypePassword = registerModel.getRetypePassword();
        boolean validMail = false;

        // Check format of email
        if (CommonUtil.isNNandNB(mail)) {
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);

            Matcher matcher = pattern.matcher(mail);
            if (!matcher.matches()) {
                errors.rejectValue(FIELD_MAIL, "Mail_wrong_format");
            } else {
                validMail = true;
            }
        }
        // Check length
        if (CommonUtil.isNNandNB(password) && (password.length() < MIN_PASSWORD)) {
            errors.rejectValue("password", "Passwords_must_length", new Object[] {MIN_PASSWORD}, " must be " + MIN_PASSWORD + " characters at least.");
        }
                
        if (CommonUtil.isNNandNB(password) && CommonUtil.isNNandNB(retypePassword) && (!password.equals(retypePassword))) {
             errors.rejectValue("matchedPassword", "Passwords_must_match");
        } else {
         // Do nothing
        }
        
        
        String company = registerModel.getCompany();
        
        // Check length of company
        if ((company != null) && (company.length() < MIN_COMPANY_NAME)) {
            errors.rejectValue(FIELD_COMPANY, "Company_min_length", new Object[] {MIN_COMPANY_NAME}, " must be " + MIN_COMPANY_NAME + " characters at least.");
        } else {
            // Do nothing
        }
        
        // Check company code is existed or not
        LdapService ldapService = new LdapService(ldapConfiguration);
        
        // to be found Group DN
        String tbfGroupDn = "ou=" + company + CHARA.COMMA + ldapConfiguration.getRootGroupOU();
        if ((company != null) && (company.length() >= MIN_COMPANY_NAME)) {
            
            Entry groupEntry = ldapService.findGroupByDN(tbfGroupDn);
            
            if (groupEntry != null) {
                // Company existed
                errors.rejectValue(FIELD_COMPANY, "Company_existed");
            } else {
                // Do nothing
            }
        } else {
            // Checked length of company
        }
        
        // Check user id is existed or not
        if (validMail) {
            // String tbfUserDn = "uid=" + mail + CHARA.COMMA + tbfGroupDn;
            Entry userEntry = ldapService.findUserByUid(mail);
            
            if (userEntry != null) {
                // Account existed
                errors.rejectValue(FIELD_MAIL, MSGCD_V001);
            } else {
                // Check user name in database
                ExUserJpaController userJpaCtrl = new ExUserJpaController(BaseService.getEmf());
                User user = userJpaCtrl.findUserByUsername(mail);

                if (user != null) {
                    errors.rejectValue(FIELD_MAIL, MSGCD_V001);
                } else {
                    // Do nothing
                }
            }
        } else {
            // Checked format of mail 
        }
    }

}
