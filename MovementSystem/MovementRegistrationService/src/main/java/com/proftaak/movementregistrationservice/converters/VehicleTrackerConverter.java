package com.proftaak.movementregistrationservice.converters;

import com.proftaak.movementregistrationservice.models.VehicleTracker;
import com.proftaak.movementregistrationservice.service.RegistrationService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class VehicleTrackerConverter {

	@Inject
	VehicleConverter vehicleConverter;
	@Inject
	TrackerConverter trackerConverter;
	@Inject
	RegistrationService registrationService;

	public VehicleTracker toEntity(com.proftaak.movementregistrationservice.shared.VehicleTracker shared) {
		return registrationService.getVehicleTracker(shared.getVehicle().getId(), shared.getTracker().getId());
	}

	public com.proftaak.movementregistrationservice.shared.VehicleTracker toShared(VehicleTracker entity) {
		return new com.proftaak.movementregistrationservice.shared.VehicleTracker(
				vehicleConverter.toShared(entity.getVehicle()),
				trackerConverter.toShared(entity.getTracker()),
				entity.getStartDate(),
				entity.getEndDate()
		);
	}

	public List<com.proftaak.movementregistrationservice.shared.VehicleTracker> toShared(List<VehicleTracker> entities) {
		List<com.proftaak.movementregistrationservice.shared.VehicleTracker> sharedModels = new ArrayList<>();
		for (VehicleTracker vehicleTracker : entities) {
			sharedModels.add(toShared(vehicleTracker));
		}

		return sharedModels;
	}
}
