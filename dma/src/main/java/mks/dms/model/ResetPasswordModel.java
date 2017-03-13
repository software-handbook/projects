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
package mks.dms.model;

import java.io.Serializable;

/**
 * @author ThachLN
 */
public class ResetPasswordModel implements Serializable {
    private String email;

    // Data for response
    private Boolean result = null;
    private String errorCode;
    

    /**
     * Get value of result.
     * @return the result
     */
    public Boolean getResult() {
        return result;
    }

    /**
     * Set the value for result.
     * @param result the result to set
     */
    public void setResult(Boolean result) {
        this.result = result;
    }

    /**
     * Get value of errorCode.
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Set the value for errorCode.
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Get value of email.
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value for email.
     * 
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
