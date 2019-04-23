package com.proftaak.usersystem.service;

import com.proftaak.usersystem.dao.UserDao;
import com.proftaak.usersystem.dao.VehicleDao;
import com.proftaak.usersystem.models.ClientUser;
import com.proftaak.usersystem.models.Vehicle;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class VehicleService {
    @Inject
    private VehicleDao userDao;

    public boolean addCarToUser(ClientUser user, String chassisNumber){
        Vehicle vehicle = new Vehicle();
        vehicle.setChassisNumber(chassisNumber);
        vehicle.setOwner( user );
        userDao.save(vehicle);
        return true;
    }
}
