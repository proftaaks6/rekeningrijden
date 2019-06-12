package com.proftaak.usersystem.service;

import com.proftaak.usersystem.dao.UserDao;
import com.proftaak.usersystem.models.ClientUser;
import com.proftaak.usersystem.models.UserVehicle;
import com.proftaak.usersystem.util.RestCommuncationHelper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@Stateless
public class UserService {
    @Inject
    private UserDao userDao;

    public ClientUser saveUserInformation(String name, String email, String address, String residence) throws IOException {
        ClientUser user = userDao.saveUserInformation(name, email, address, residence);

        // Create this user in driversystem and invoicesystem
        if(System.getenv("environment") != null && System.getenv("environment").equals("production")) {
            RestCommuncationHelper.postRequest("http://driversystem:8080/deploy/v1/driverapplication/createUser",
                    "username=" + name + "&password=welkom123");
            RestCommuncationHelper.postRequest("http://invoicesystem:8080/deploy/v1/vehicleprocessing/vehicle/" + user.getId());
        } else {
            RestCommuncationHelper.postRequest("http://localhost:8080/DriverSystem/v1/driverapplication/createUser",
                    "username=" + name + "&password=welkom123");
            RestCommuncationHelper.postRequest("http://localhost:8080/InvoiceSystem/v1/vehicleprocessing/vehicle/" + user.getId());
        }

        return user;
    }

    public ClientUser getClientUserByName(String name) {
        return userDao.getClientUserByName(name);

    }

    public ClientUser getClientUserById(long id){
        return userDao.getClientUserById(id);
    }

    public List<ClientUser> getAllUsers()
    {
        return userDao.getAll();
    }

    public ClientUser editUser(ClientUser user){return userDao.editUser(user);}

    public UserVehicle getUserVehicle(long vehicleId, long userId) {
        return userDao.getUserVehicle(vehicleId, userId);
    }
}
