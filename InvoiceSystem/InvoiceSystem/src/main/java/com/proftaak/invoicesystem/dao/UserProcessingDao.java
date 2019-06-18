package com.proftaak.invoicesystem.dao;


import com.proftaak.invoicesystem.models.UserProcessingState;

import java.util.List;
import javax.persistence.EntityManager;

public interface UserProcessingDao {
    boolean addNewUser(long userId);
    List<UserProcessingState> getAllUsers();
    UserProcessingState getUserById(long userId);
    void setEm(EntityManager em);
    EntityManager getEm();
}
