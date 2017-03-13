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
@Table(name = "read_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReadStatus.findAll", query = "SELECT r FROM ReadStatus r"),
    @NamedQuery(name = "ReadStatus.findById", query = "SELECT r FROM ReadStatus r WHERE r.id = :id"),
    @NamedQuery(name = "ReadStatus.findByCreated", query = "SELECT r FROM ReadStatus r WHERE r.created = :created"),
    @NamedQuery(name = "ReadStatus.findByCreatedbyUsername", query = "SELECT r FROM ReadStatus r WHERE r.createdbyUsername = :createdbyUsername"),
    @NamedQuery(name = "ReadStatus.findByEmail", query = "SELECT r FROM ReadStatus r WHERE r.email = :email"),
    @NamedQuery(name = "ReadStatus.findByIsRead", query = "SELECT r FROM ReadStatus r WHERE r.isRead = :isRead"),
    @NamedQuery(name = "ReadStatus.findByLastmodified", query = "SELECT r FROM ReadStatus r WHERE r.lastmodified = :lastmodified"),
    @NamedQuery(name = "ReadStatus.findByLastmodifiedbyUsername", query = "SELECT r FROM ReadStatus r WHERE r.lastmodifiedbyUsername = :lastmodifiedbyUsername"),
    @NamedQuery(name = "ReadStatus.findByReqId", query = "SELECT r FROM ReadStatus r WHERE r.reqId = :reqId"),
    @NamedQuery(name = "ReadStatus.findByReqStatus", query = "SELECT r FROM ReadStatus r WHERE r.reqStatus = :reqStatus"),
    @NamedQuery(name = "ReadStatus.findByUsername", query = "SELECT r FROM ReadStatus r WHERE r.username = :username")})
public class ReadStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "CREATEDBY_USERNAME")
    private String createdbyUsername;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "IS_READ")
    private Integer isRead;
    @Column(name = "LASTMODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;
    @Column(name = "LASTMODIFIEDBY_USERNAME")
    private String lastmodifiedbyUsername;
    @Column(name = "REQ_ID")
    private Integer reqId;
    @Column(name = "REQ_STATUS")
    private String reqStatus;
    @Column(name = "USERNAME")
    private String username;

    public ReadStatus() {
    }

    public ReadStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
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

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        if (!(object instanceof ReadStatus)) {
            return false;
        }
        ReadStatus other = (ReadStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mks.dms.dao.entity.ReadStatus[ id=" + id + " ]";
    }
    
}
