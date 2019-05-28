package com.proftaak.usersystem.service;

import com.proftaak.usersystem.dao.UserDao;
import com.proftaak.usersystem.models.ClientUser;
import com.proftaak.usersystem.models.UserVehicle;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {
    @Inject
    private UserDao userDao;

    public ClientUser saveUserInformation(String name, String email, String address, String residence) {
        return userDao.saveUserInformation(name, email, address, residence);
    }

    public ClientUser getClientUserByName(String name) {
        return userDao.getClientUserByName(name);
    }

    public ClientUser getClientUserById(int id){
        return userDao.getClientUserById(id);
    }

    public List<ClientUser> getAllUsers()
    {
        return userDao.getAll();
    }

    public UserVehicle getUserVehicle(long vehicleId, long userId) {
        return userDao.getUserVehicle(vehicleId, userId);
    }
}
