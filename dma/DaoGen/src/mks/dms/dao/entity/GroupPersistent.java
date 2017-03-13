/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mks.dms.dao.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ThachLe
 */
@Entity
@Table(name = "group_persistent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupPersistent.findAll", query = "SELECT g FROM GroupPersistent g"),
    @NamedQuery(name = "GroupPersistent.findById", query = "SELECT g FROM GroupPersistent g WHERE g.id = :id"),
    @NamedQuery(name = "GroupPersistent.findByGroupId", query = "SELECT g FROM GroupPersistent g WHERE g.groupId = :groupId"),
    @NamedQuery(name = "GroupPersistent.findByDbname", query = "SELECT g FROM GroupPersistent g WHERE g.dbname = :dbname"),
    @NamedQuery(name = "GroupPersistent.findByJdbcDriver", query = "SELECT g FROM GroupPersistent g WHERE g.jdbcDriver = :jdbcDriver"),
    @NamedQuery(name = "GroupPersistent.findByJdbcUrl", query = "SELECT g FROM GroupPersistent g WHERE g.jdbcUrl = :jdbcUrl"),
    @NamedQuery(name = "GroupPersistent.findByJdbcUsername", query = "SELECT g FROM GroupPersistent g WHERE g.jdbcUsername = :jdbcUsername"),
    @NamedQuery(name = "GroupPersistent.findByJdbcPassword", query = "SELECT g FROM GroupPersistent g WHERE g.jdbcPassword = :jdbcPassword"),
    @NamedQuery(name = "GroupPersistent.findByCreated", query = "SELECT g FROM GroupPersistent g WHERE g.created = :created"),
    @NamedQuery(name = "GroupPersistent.findByCreatedbyUsername", query = "SELECT g FROM GroupPersistent g WHERE g.createdbyUsername = :createdbyUsername"),
    @NamedQuery(name = "GroupPersistent.findByLastmodified", query = "SELECT g FROM GroupPersistent g WHERE g.lastmodified = :lastmodified"),
    @NamedQuery(name = "GroupPersistent.findByLastmodifiedbyUsername", query = "SELECT g FROM GroupPersistent g WHERE g.lastmodifiedbyUsername = :lastmodifiedbyUsername")})
public class GroupPersistent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "GROUP_ID")
    private String groupId;
    @Basic(optional = false)
    @Column(name = "DBNAME")
    private String dbname;
    @Basic(optional = false)
    @Column(name = "JDBC_DRIVER")
    private String jdbcDriver;
    @Basic(optional = false)
    @Column(name = "JDBC_URL")
    private String jdbcUrl;
    @Basic(optional = false)
    @Column(name = "JDBC_USERNAME")
    private String jdbcUsername;
    @Basic(optional = false)
    @Column(name = "JDBC_PASSWORD")
    private String jdbcPassword;
    @Basic(optional = false)
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "CREATEDBY_USERNAME")
    private String createdbyUsername;
    @Column(name = "LASTMODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;
    @Column(name = "LASTMODIFIEDBY_USERNAME")
    private String lastmodifiedbyUsername;

    public GroupPersistent() {
    }

    public GroupPersistent(Integer id) {
        this.id = id;
    }

    public GroupPersistent(Integer id, String groupId, String dbname, String jdbcDriver, String jdbcUrl, String jdbcUsername, String jdbcPassword, Date created, String createdbyUsername) {
        this.id = id;
        this.groupId = groupId;
        this.dbname = dbname;
        this.jdbcDriver = jdbcDriver;
        this.jdbcUrl = jdbcUrl;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedbyUsername() {
        return createdbyUsername;
    }

    public void setCreatedbyUsername(String createdbyUsername) {
        this.createdbyUsername = createdbyUsername;
    }

    public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    public String getLastmodifiedbyUsername() {
        return lastmodifiedbyUsername;
    }

    public void setLastmodifiedbyUsername(String lastmodifiedbyUsername) {
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupPersistent)) {
            return false;
        }
        GroupPersistent other = (GroupPersistent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mks.dms.dao.entity.GroupPersistent[ id=" + id + " ]";
    }
    
}
