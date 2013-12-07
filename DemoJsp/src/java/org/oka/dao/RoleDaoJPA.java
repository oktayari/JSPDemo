
package org.oka.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.oka.model.Rolest;


@Stateless
@Named
@Default
//@Alternative
public class RoleDaoJPA implements IRoleDao {

     @PersistenceContext(unitName = "DemoJspPU")
    private EntityManager em;
     
    protected EntityManager getEntityManager() {
        return em;
    }
     
    @Override
    public void addRole(Rolest role) {
         getEntityManager().persist(role);
    }

    @Override
    public void editRole(Rolest role) {
         getEntityManager().merge(role);
    }

    @Override
    public void deleteRole(String roleName) {
        getEntityManager().remove(getRole(roleName));
    }

    @Override
    public Rolest getRole(String roleName) {
        Rolest role = getEntityManager().find(Rolest.class, roleName);
        return role;
    }

    @Override
    public List<Rolest> getAllRoles() {
       getEntityManager().getEntityManagerFactory().getCache().evictAll();
        List<Rolest> roleList = getEntityManager().createNamedQuery("Rolest.findAll").getResultList();
        return roleList;
    }

    @Override
    public int count() {
        return getEntityManager().createNamedQuery("Rolest.findAll").getResultList().size();
    }

    @Override
    public List<Rolest> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Rolest.class));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
}
