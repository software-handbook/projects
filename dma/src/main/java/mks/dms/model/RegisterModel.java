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

import org.hibernate.validator.constraints.Email;

/**
 * @author ThachLN
 *
 */
public class RegisterModel implements Serializable {
    private String mail;
    private String password;
    private String retypePassword;
    private String company;

    private boolean matchedPassword = false;
    /**
     * Get value of retypePassword.
     * @return the retypePassword
     */
    public String getRetypePassword() {
        return retypePassword;
    }
    /**
     * Set the value for retypePassword.
     * @param retypePassword the retypePassword to set
     */
    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    /**
     * Get value of mail.
     * @return the mail
     */
    public String getMail() {
        return mail;
    }
    /**
     * Set the value for mail.
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
    /**
     * Get value of password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Set the value for password.
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get value of company.
     * @return the company
     */
    public String getCompany() {
        return company;
    }
    /**
     * Set the value for company.
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }
    /**
     * Get value of matchedPassword.
     * @return the matchedPassword
     */
    public boolean isMatchedPassword() {
        return matchedPassword;
    }
    /**
     * Set the value for matchedPassword.
     * @param matchedPassword the matchedPassword to set
     */
    public void setMatchedPassword(boolean matchedPassword) {
        this.matchedPassword = matchedPassword;
    }
    
}
