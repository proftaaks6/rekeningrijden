package com.proftaak.usersystem.dao;

import com.proftaak.usersystem.models.Vehicle;

public interface VehicleDao {
    void save(Vehicle vehicle);
    Vehicle getByChassis(String chassis);

}
