package com.proftaak.usersystem.converters;

import com.proftaak.usersystem.models.UserVehicle;
import com.proftaak.usersystem.service.UserService;
import com.proftaak.usersystem.shared.ClientUser;
import com.proftaak.usersystem.shared.Vehicle;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserVehicleConverter
{
	@Inject
	private UserService userService;

	@Inject VehicleConverter vehicleConverter;

	@Inject ClientUserConverter clientUserConverter;

	public UserVehicle toEntity(com.proftaak.usersystem.shared.UserVehicle userVehicle) {
		setConverters();

		return userService.getUserVehicle(userVehicle.getVehicle().getId(), userVehicle.getUser().getId());
	}

	public com.proftaak.usersystem.shared.UserVehicle toShared(UserVehicle userVehicle) {
		setConverters();

		return new com.proftaak.usersystem.shared.UserVehicle(
			vehicleConverter.toShared(userVehicle.getVehicle()),
			clientUserConverter.toShared(userVehicle.getUser()),
			userVehicle.getStartDate(),
			userVehicle.getEndDate()
		);
	}

	public List<com.proftaak.usersystem.shared.UserVehicle> toShared (List<UserVehicle> userVehicles) {
		setConverters();

		List<com.proftaak.usersystem.shared.UserVehicle> shared = new ArrayList<>();

		for (UserVehicle userVehicle : userVehicles)
		{
			shared.add(toShared(userVehicle));

		}

		return shared;
	}

	private void setConverters() {
		if (vehicleConverter == null || clientUserConverter == null) {
			vehicleConverter = new VehicleConverter();
			clientUserConverter = new ClientUserConverter();
		}
	}
}
