package com.proftaak.driverapplication.converters;

import com.proftaak.driverapplication.models.DriverUser;
import com.proftaak.driverapplication.service.DriverApplicationService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DriverUserConverter
{
	@Inject
	private DriverApplicationService driverApplicationService;

	public DriverUser toEntity(com.proftaak.driverapplication.shared.DriverUser user) {
		return driverApplicationService.getUserById(user.getId());
	}

	public com.proftaak.driverapplication.shared.DriverUser toShared(DriverUser user) {
		return new com.proftaak.driverapplication.shared.DriverUser(user.getId());
	}

	public List<com.proftaak.driverapplication.shared.DriverUser> toShared(List<DriverUser> users) {
		List<com.proftaak.driverapplication.shared.DriverUser> shared = new ArrayList<>();
		for (DriverUser user : users)
		{
			shared.add(toShared(user));
		}
		return shared;
	}
}
