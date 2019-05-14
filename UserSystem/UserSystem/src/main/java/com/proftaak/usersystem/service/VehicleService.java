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
    private VehicleDao vehicleDao;

    public boolean addCarToUser(ClientUser user, String chassisNumber){
        Vehicle vehicle = new Vehicle();
        vehicle.setOwner( user );
        vehicle.setChassisNumber(chassisNumber);
        vehicleDao.save(vehicle);
        return true;
    }
}
