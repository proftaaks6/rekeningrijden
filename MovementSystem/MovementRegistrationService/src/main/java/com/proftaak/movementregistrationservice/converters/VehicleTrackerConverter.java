package com.proftaak.movementregistrationservice.converters;

import com.proftaak.movementregistrationservice.models.VehicleTracker;
import com.proftaak.movementregistrationservice.service.RegistrationService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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

	private void setConverters() {
		if (vehicleConverter == null || trackerConverter == null) {
			vehicleConverter = new VehicleConverter();
			trackerConverter = new TrackerConverter();
		}
	}

	public com.proftaak.movementregistrationservice.shared.VehicleTracker toShared(VehicleTracker entity) {
		setConverters();

		return new com.proftaak.movementregistrationservice.shared.VehicleTracker(
				vehicleConverter.toShared(entity.getVehicle()),
				trackerConverter.toShared(entity.getTracker()),
				entity.getStartDate(),
				entity.getEndDate()
		);
	}

	public com.proftaak.movementregistrationservice.shared.VehicleTracker toShared(VehicleTracker entity, boolean withoutVehicle) {
		setConverters();

		return new com.proftaak.movementregistrationservice.shared.VehicleTracker(
				withoutVehicle ? null : vehicleConverter.toShared(entity.getVehicle()),
				trackerConverter.toShared(entity.getTracker()),
				entity.getStartDate(),
				entity.getEndDate()
		);
	}

	public List<com.proftaak.movementregistrationservice.shared.VehicleTracker> toShared(List<VehicleTracker> entities, boolean withoutVehicle) {
		setConverters();

		List<com.proftaak.movementregistrationservice.shared.VehicleTracker> sharedModels = new ArrayList<>();
		for (VehicleTracker vehicleTracker : entities) {
			sharedModels.add(toShared(vehicleTracker, withoutVehicle));
		}

		return sharedModels;
	}

	public List<com.proftaak.movementregistrationservice.shared.VehicleTracker> toShared(List<VehicleTracker> entities) {
		setConverters();

		List<com.proftaak.movementregistrationservice.shared.VehicleTracker> sharedModels = new ArrayList<>();
		for (VehicleTracker vehicleTracker : entities) {
			sharedModels.add(toShared(vehicleTracker));
		}

		return sharedModels;
	}
}
