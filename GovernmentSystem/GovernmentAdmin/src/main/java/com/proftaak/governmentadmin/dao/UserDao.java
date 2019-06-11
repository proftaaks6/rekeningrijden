package com.proftaak.governmentadmin.dao;

import com.proftaak.governmentadmin.models.GovernmentEmployee;

import javax.persistence.EntityManager;
import java.util.List;

public interface UserDao {
    boolean addUser(GovernmentEmployee user);
    boolean removeUser(GovernmentEmployee user);
    boolean updateUser(GovernmentEmployee user);
    GovernmentEmployee findByUsername(String username);
    List<GovernmentEmployee> getAll();
    GovernmentEmployee validateUser(String username, String password);
    void setEm(EntityManager em);
    EntityManager getEm();
}
