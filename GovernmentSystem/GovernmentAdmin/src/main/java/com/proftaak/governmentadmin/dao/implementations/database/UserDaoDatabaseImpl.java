package com.proftaak.governmentadmin.dao.implementations.database;

import com.proftaak.governmentadmin.dao.UserDao;
import com.proftaak.governmentadmin.models.GovernmentEmployee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserDaoDatabaseImpl implements UserDao {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    public boolean addUser(GovernmentEmployee user) {
        em.persist(user);
        return true;
    }

    @Override
    public boolean removeUser(GovernmentEmployee user) {
        em.remove(user);
        return true;
    }

    @Override
    public boolean updateUser(GovernmentEmployee user) {
        em.merge(user);

        return true;
    }

    @Override
    public GovernmentEmployee findByUsername(String username) {
        try{
            return em.createNamedQuery("GovernmentEmployee.findByUsername", GovernmentEmployee.class).setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    @Override
    public List<GovernmentEmployee> getAll() {
        return em.createNamedQuery("GovernmentEmployee.getAll", GovernmentEmployee.class).getResultList();
    }

    @Override
    public GovernmentEmployee validateUser(String username, String password) {
        try {
            return em.createNamedQuery("GovernmentEmployee.validateUser", GovernmentEmployee.class).setParameter("username", username).setParameter("password", password).setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public EntityManager getEm() {
        return this.em;
    }
}
