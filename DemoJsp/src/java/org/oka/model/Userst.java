package org.oka.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "userst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userst.findAll", 
            query = "SELECT u FROM Userst u"),
    @NamedQuery(name = "Userst.findByUserName", 
            query = "SELECT u FROM Userst u WHERE u.userName = :userName"),
    @NamedQuery(name = "Userst.findByFirstName", 
            query = "SELECT u FROM Userst u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "Userst.findByLastName", 
            query = "SELECT u FROM Userst u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "Userst.findByPassWord", 
            query = "SELECT u FROM Userst u WHERE u.passWord = :passWord"),
    @NamedQuery(name = "Userst.findByEmail", 
            query = "SELECT u FROM Userst u WHERE u.email = :email"),
    @NamedQuery(name = "Userst.findByCreationDate", 
            query = "SELECT u FROM Userst u WHERE u.creationDate = :creationDate")})
public class Userst implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "userName")
    private String userName;
    @Size(max = 20)
    @Column(name = "firstName")
    private String firstName;
    @Size(max = 20)
    @Column(name = "lastName")
    private String lastName;
    @Size(max = 8)
    @Column(name = "passWord")
    private String passWord;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.
    //[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*
    //[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", 
    //message="Invalid email")
    //if the field contains email address consider using 
    //this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "creationDate",updatable = false,insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

  
    
     @ManyToMany
     @JoinTable(name = "userroles", joinColumns = {
        @JoinColumn(name = "userName", referencedColumnName = "userName")}, 
            inverseJoinColumns = {
        @JoinColumn(name = "roleName", referencedColumnName = "roleName")})
    private Collection<Rolest> rolestCollection;

    public Userst() {
        rolestCollection = new ArrayList<>();
    }

    public Userst(String userName) {
        this.userName = userName;
         rolestCollection = new ArrayList<>();
    }

    public Userst(String userName, Date creationDate) {
        this.userName = userName;
        this.creationDate = creationDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @XmlTransient
    public Collection<Rolest> getRolestCollection() {
        return rolestCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userName != null ? userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userst)) {
            return false;
        }
        Userst other = (Userst) object;
        return (this.userName != null || other.userName == null) && 
                (this.userName == null || this.userName.equals(other.userName));
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
