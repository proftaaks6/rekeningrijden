package com.proftaak.usersystem.dao.impl;

import com.proftaak.usersystem.dao.UserDao;
import com.proftaak.usersystem.models.ClientUser;
import com.proftaak.usersystem.models.Vehicle;
import com.proftaak.usersystem.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Stateless
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean saveUserInformation(String name, String email, String address, String residence) {
        try {
            em.persist(new ClientUser(name, address, residence, new ArrayList<Vehicle>(), email));

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public ClientUser getClientUserByName(String name) {
        try {
            return em.createNamedQuery("ClientUser.getByName", ClientUser.class).setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
