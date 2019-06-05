package com.proftaak.usersystem.service;

import com.proftaak.usersystem.dao.UserDao;
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
    @Inject
    private UserDao userDao;

    public boolean addCarToUser(ClientUser user, String chassisNumber){
        Vehicle vehicle = new Vehicle();
        vehicle.setChassisNumber(chassisNumber);
        UserVehicle userVehicle = new UserVehicle(vehicle, user, new Date());
        user.addOwnedVehicle(userVehicle);
        userDao.editUser(user);
        return true;
    }

    public Vehicle getByChassis(String chassis) {
        return vehicleDao.getByChassis(chassis);
    }
}
