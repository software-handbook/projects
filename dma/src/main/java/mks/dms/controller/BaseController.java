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

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import mks.dms.dao.entity.User;
import mks.dms.extentity.ExUser;
import mks.dms.util.AppUtil;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * @author ThachLN
 *
 */
public class BaseController {
    private final static Logger LOG = Logger.getLogger(BaseController.class);

    public static final String KEY_GROUP = "_KEY_GROUP";
    static final String DEFAULT_GROUP = "dma-master";
    
    /** Unique identifer of company.*/
    String groupId;

    /**
    * Get group name from the DN value of principal.
    * @param principal
    * @return
    */
    public String getGroup(Principal principal) {
        String group = null;

        if (principal instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) principal;
            
            LOG.debug("principal= " + principal.toString());
            
            LOG.debug("authToken.getPrincipal()=" + authToken.getPrincipal());
            
            if (authToken.getPrincipal() instanceof LdapUserDetailsImpl) {
                LdapUserDetailsImpl ldapUd = (LdapUserDetailsImpl) authToken.getPrincipal();
                String dn = ldapUd.getDn();
                
                // ex of dn: uid=admin,ou=Users,dc=maxcrc,dc=com
                LOG.debug("dn=" + dn);
                group = AppUtil.parseGroup(dn);
                
                LOG.debug("group=" + group);
                
            } else {
                LOG.warn("Unknown authToken.getPrincipal()=" + authToken.getPrincipal());
            }
            
            LOG.debug("authToken.getDetails()=" + authToken.getDetails());
            if (authToken.getDetails() instanceof WebAuthenticationDetails) {
                WebAuthenticationDetails wad = (WebAuthenticationDetails) authToken.getDetails();
                LOG.debug("RemoteAddress=" + wad.getRemoteAddress());
            } else {
                LOG.warn("Unknown authToken.getDetails()=" + authToken.getDetails());
            }
            
            LOG.debug("authToken.getCredentials()=" + authToken.getCredentials());
            Collection<GrantedAuthority> grantedAuth = authToken.getAuthorities();
            GrantedAuthority ga;
            for (Iterator<GrantedAuthority> it = grantedAuth.iterator(); it.hasNext(); ) {
                ga = it.next();
                LOG.debug("ga.getAuthority()=" + ga.getAuthority());
            }
            
        } else {
            LOG.warn("Unknown Principal = " + principal);
        }
        
        return group;
    }

    /**
    * Get group name from the DN value of principal.
    * @param principal
    * @return ExUser with information:
    * - dn
    * - groupId
    * - username
    * - enabled flag
    */
    public User parsePrincipal(Principal principal) {
        User user = null;

        if (principal instanceof UsernamePasswordAuthenticationToken) {
            user = new User();
            UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) principal;
            
            LOG.debug("principal= " + principal.toString());
            
            LOG.debug("authToken.getPrincipal()=" + authToken.getPrincipal());
            
            if (authToken.getPrincipal() instanceof LdapUserDetailsImpl) {
                LdapUserDetailsImpl ldapUd = (LdapUserDetailsImpl) authToken.getPrincipal();
                String dn = ldapUd.getDn();
                
                // ex of dn: uid=admin,ou=Users,dc=maxcrc,dc=com
                LOG.debug("dn=" + dn);
                
                String group = AppUtil.parseGroup(dn);
                //user.setDn(dn);
                user.setGroupId(group);
                user.setUsername(principal.getName());
                user.setEnabled(ldapUd.isEnabled());
                
                LOG.debug("group=" + group);
                
            } else {
                LOG.warn("Unknown authToken.getPrincipal()=" + authToken.getPrincipal());
            }
            
            LOG.debug("authToken.getDetails()=" + authToken.getDetails());
            if (authToken.getDetails() instanceof WebAuthenticationDetails) {
                WebAuthenticationDetails wad = (WebAuthenticationDetails) authToken.getDetails();
                LOG.debug("RemoteAddress=" + wad.getRemoteAddress());
            } else {
                LOG.warn("Unknown authToken.getDetails()=" + authToken.getDetails());
            }
            
            LOG.debug("authToken.getCredentials()=" + authToken.getCredentials());
            Collection<GrantedAuthority> grantedAuth = authToken.getAuthorities();
            GrantedAuthority ga;
            for (Iterator<GrantedAuthority> it = grantedAuth.iterator(); it.hasNext(); ) {
                ga = it.next();
                LOG.debug("ga.getAuthority()=" + ga.getAuthority());
            }
            
        } else {
            LOG.warn("Unknown Principal = " + principal);
        }
        
        return user;
    }
    
    public String getGroup(Principal principal, HttpSession session) {
        String group = (String) session.getAttribute(KEY_GROUP);
        
        if (group == null) {
            group = getGroup(principal);
            session.setAttribute(KEY_GROUP, group);
        } else {
            // Do nothing
        }

        return group;
    }
    
    protected boolean isInRole(Principal principal, String role) {
        boolean isInRole = false;
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) principal;
         
            Collection<GrantedAuthority> grantedAuth = authToken.getAuthorities();
            GrantedAuthority ga;
            for (Iterator<GrantedAuthority> it = grantedAuth.iterator(); it.hasNext() && (!isInRole); ) {
                ga = it.next();
                if (role.equalsIgnoreCase(ga.getAuthority())) {
                    isInRole = true;
                } else if (ga instanceof SimpleGrantedAuthority) {
                    
                }
            }
        }
        
        return isInRole;
    }
    
    /**
     * Get value of the groupName.
     * @return the groupName
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Set value for the groupName
     * @param groupName the groupName to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
