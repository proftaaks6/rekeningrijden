package com.proftaak.usersystem.converters;

import com.proftaak.usersystem.models.Vehicle;
import com.proftaak.usersystem.service.VehicleService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class VehicleConverter
{
	@Inject
	private VehicleService vehicleService;
	@Inject ClientUserConverter clientUserConverter;
	@Inject UserVehicleConverter userVehicleConverter;

	public Vehicle toEntity(com.proftaak.usersystem.shared.Vehicle vehicle) {
		return vehicleService.getByChassis(vehicle.getChassisNumber());
	}

	public com.proftaak.usersystem.shared.Vehicle toShared(Vehicle vehicle) {
		return new com.proftaak.usersystem.shared.Vehicle(
				vehicle.getChassisNumber(),
				userVehicleConverter.toShared(vehicle.getOwners())
		);
	}

	public List<com.proftaak.usersystem.shared.Vehicle> toShared(List<Vehicle> vehicles) {
		List<com.proftaak.usersystem.shared.Vehicle> shared = new ArrayList<>();
		for (Vehicle vehicle : vehicles)
		{
			shared.add(toShared(vehicle));
		}
		return shared;
	}
}
