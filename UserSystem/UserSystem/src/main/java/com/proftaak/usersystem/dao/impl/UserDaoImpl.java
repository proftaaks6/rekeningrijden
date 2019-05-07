package com.proftaak.usersystem.dao.impl;

import com.proftaak.usersystem.dao.UserDao;
import com.proftaak.usersystem.models.ClientUser;
import com.proftaak.usersystem.models.Vehicle;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Stateless
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ClientUser saveUserInformation(String name, String email, String address, String residence) {
        try {
            ClientUser user = new ClientUser(name, address, residence, new ArrayList<>(), email);
            em.persist(user);
            em.flush();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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

    @Override
    public ClientUser getClientUserById(int id) {
        try {
            return em.createNamedQuery("ClientUser.getById", ClientUser.class).setParameter("id", id).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ClientUser> getAll()
    {
        try {
            return em.createNamedQuery("ClientUser.getAll", ClientUser.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
