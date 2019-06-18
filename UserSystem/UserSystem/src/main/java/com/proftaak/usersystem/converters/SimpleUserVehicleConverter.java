package com.proftaak.usersystem.converters;

import com.proftaak.usersystem.models.ClientUser;
import com.proftaak.usersystem.models.UserVehicle;
import com.proftaak.usersystem.shared.SimpleUserVehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.ejb.Stateless;

@Stateless
public class SimpleUserVehicleConverter
{
	public List<SimpleUserVehicle> fromUser(ClientUser user, Date start, Date end) {
		List<SimpleUserVehicle> list = new ArrayList<>();
		for (UserVehicle v : user.getOwnedVehicles()) {
			if ((v.getEndDate() == null ||
					(v.getEndDate() != null && start.compareTo(v.getEndDate()) <= 0)) &&
					end.compareTo(v.getStartDate()) >= 0) {

				SimpleUserVehicle userVehicleLink = new SimpleUserVehicle();
				userVehicleLink.setChassisNumber(v.getVehicle().getChassisNumber());
				userVehicleLink.setEndDate(v.getEndDate());
				userVehicleLink.setStartDate(v.getStartDate());
				list.add(userVehicleLink);
			}
		}

		return list;
	}
}
