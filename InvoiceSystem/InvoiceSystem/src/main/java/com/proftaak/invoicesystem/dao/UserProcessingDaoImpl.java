package com.proftaak.invoicesystem.dao;

import com.proftaak.invoicesystem.models.UserProcessingState;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class UserProcessingDaoImpl implements UserProcessingDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean addNewUser(long userId) {
        try {
            em.persist(new UserProcessingState(userId));
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    @Override
    public List<UserProcessingState> getAllUsers() {
        try {
            return em.createNamedQuery("UserProcessingState.getAll", UserProcessingState.class).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public UserProcessingState getUserById(long userId) {
        return em.createNamedQuery("UserProcessingState.getByUserId", UserProcessingState.class).setParameter("userId", userId).getSingleResult();
    }
}
