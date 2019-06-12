package com.proftaak.usersystem.dao;

import com.proftaak.usersystem.models.UserVehicle;

public interface UserVehicleDao
{
	UserVehicle getActiveForVehicle(String chassis);
	UserVehicle editUserVehicle(UserVehicle userVehicle);
}
