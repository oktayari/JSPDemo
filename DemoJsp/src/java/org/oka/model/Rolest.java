
package org.oka.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "rolest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolest.findAll", 
            query = "SELECT r FROM Rolest r"),
    @NamedQuery(name = "Rolest.findByRoleName", 
            query = "SELECT r FROM Rolest r WHERE r.roleName = :roleName")})
public class Rolest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "roleName")
    private String roleName;
    
   
    @ManyToMany(mappedBy = "rolestCollection")
    private final Collection<Userst> userstCollection;

    public Rolest() {
        userstCollection = new ArrayList<>();
    }

    public Rolest(String roleName) {
        this.roleName = roleName;
         userstCollection = new ArrayList<>();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @XmlTransient
    public Collection<Userst> getUserstCollection() {
        return userstCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleName != null ? roleName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rolest)) {
            return false;
        }
        Rolest other = (Rolest) object;
        return (this.roleName != null || other.roleName == null) && 
                (this.roleName == null || this.roleName.equals(other.roleName));
    }

    @Override
    public String toString() {
        return  roleName;
    }
    
}
