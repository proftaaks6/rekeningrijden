package com.proftaak.driverapplication.dao.impl;

import com.proftaak.driverapplication.dao.UserDao;
import com.proftaak.driverapplication.models.DriverUser;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDaoImpl implements UserDao
{

    @PersistenceContext
    private EntityManager em;

    @Override
    public DriverUser saveNewUser(long id, String password)
    {
        try {
            DriverUser user = new DriverUser(id, password);
            em.persist(user);
            em.flush();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public DriverUser getDriverUserById(long id)
    {
        try {
            return em.createNamedQuery("DriverUser.getById", DriverUser.class).setParameter("id", id).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<DriverUser> getAll()
    {
        try {
            return em.createNamedQuery("DriverUser.getAll", DriverUser.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
