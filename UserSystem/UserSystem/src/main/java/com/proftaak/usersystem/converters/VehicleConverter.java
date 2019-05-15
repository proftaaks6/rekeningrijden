package com.proftaak.usersystem.converters;

import com.proftaak.usersystem.models.ClientUser;
import com.proftaak.usersystem.models.Vehicle;
import com.proftaak.usersystem.service.UserService;
import com.proftaak.usersystem.service.VehicleService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class VehicleConverter
{
	@Inject
	private VehicleService vehicleService;
	@Inject ClientUserConverter clientUserConverter;

	public Vehicle toEntity(com.proftaak.usersystem.shared.Vehicle vehicle) {
		return vehicleService.getByChassis(vehicle.getChassisNumber());
	}

	public com.proftaak.usersystem.shared.Vehicle toShared(Vehicle vehicle) {
		return new com.proftaak.usersystem.shared.Vehicle(
				vehicle.getChassisNumber(),
				clientUserConverter.toShared(vehicle.getOwner())
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
