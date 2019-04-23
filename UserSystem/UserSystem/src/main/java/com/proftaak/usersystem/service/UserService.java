package com.proftaak.usersystem.service;

import com.proftaak.usersystem.dao.UserDao;
import com.proftaak.usersystem.models.ClientUser;
import com.proftaak.usersystem.models.Vehicle;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {
    @Inject
    private UserDao userDao;

    public boolean saveUserInformation(String name, String email, String address, String residence) {
        if (userDao.saveUserInformation(name, email, address, residence)) {
            return true;
        } else {
            return false;
        }
    }

    public ClientUser getClientUserByName(String name) {
        return userDao.getClientUserByName(name);
    }
    public ClientUser getClientUserById(int id){
        return userDao.getClientUserById(id);
    }

}
