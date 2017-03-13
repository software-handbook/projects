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

import mks.dms.model.ConfirmResetPasswordModel;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import rocky.common.CommonUtil;

/**
 * @author ThachLN
 */
public class ConfirmResetPasswordModelValidator implements Validator {

    @Override
    public boolean supports(Class<?> paramClass) {
        return ConfirmResetPasswordModel.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        // Check required
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmNewPassword", "required");

        ConfirmResetPasswordModel confirmResetPasswordModel = (ConfirmResetPasswordModel) obj;
        String newPassword = confirmResetPasswordModel.getNewPassword();
        String confirmNewPassword = confirmResetPasswordModel.getConfirmNewPassword();

        
        if (CommonUtil.isNNandNB(newPassword) && CommonUtil.isNNandNB(confirmNewPassword) && (!newPassword.equals(confirmNewPassword))) {
             errors.rejectValue("matchedPassword", "Passwords_must_match");
        }
    }

}
