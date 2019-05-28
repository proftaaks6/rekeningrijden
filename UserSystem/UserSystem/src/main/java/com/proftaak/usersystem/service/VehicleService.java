package com.proftaak.usersystem.service;

import com.proftaak.usersystem.dao.VehicleDao;
import com.proftaak.usersystem.models.ClientUser;
import com.proftaak.usersystem.models.UserVehicle;
import com.proftaak.usersystem.models.Vehicle;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;

@Stateless
public class VehicleService {
    @Inject
    private VehicleDao vehicleDao;

    public boolean addCarToUser(ClientUser user, String chassisNumber){
        Vehicle vehicle = new Vehicle();
        vehicle.addOwner(new UserVehicle(vehicle, user, new Date()));
        vehicle.setChassisNumber(chassisNumber);
        vehicleDao.save(vehicle);
        return true;
    }

    public Vehicle getByChassis(String chassis) {
        return vehicleDao.getByChassis(chassis);
    }
}
