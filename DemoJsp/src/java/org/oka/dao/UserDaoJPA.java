package org.oka.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.oka.model.Userst;


@Stateless
@Named
@Default
//@Alternative
public class UserDaoJPA implements IUserDao {

    @PersistenceContext(unitName = "DemoJspPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void addUser(Userst user) {
       
        getEntityManager().persist(user);
        getEntityManager().flush();
      
    }

    @Override
    public void editUser(Userst user) {
       
        getEntityManager().merge(user);
        getEntityManager().flush();
       
    }

    @Override
    public void deleteUser(String userName) {
        getEntityManager().remove(getUser(userName));
    }

    @Override
    public Userst getUser(String userName) {
        Userst user = getEntityManager().find(Userst.class, userName);
        return user;

    }

    @Override
    public List<Userst> getAllUsers() {
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
        List<Userst> userlist = getEntityManager().createNamedQuery("Userst.findAll").getResultList();
        return userlist;
    }

    
    @Override
    public int count() {
        return getEntityManager().createNamedQuery("Userst.findAll").getResultList().size();
    }

    @Override
    public List<Userst> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Userst.class));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    

}
