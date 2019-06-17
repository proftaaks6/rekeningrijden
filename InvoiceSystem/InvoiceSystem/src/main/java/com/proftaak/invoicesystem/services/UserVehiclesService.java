package com.proftaak.invoicesystem.services;

import com.google.gson.Gson;
import com.proftaak.usersystem.shared.SimpleUserVehicle;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import java.util.Date;
import javax.enterprise.util.TypeLiteral;
import static com.proftaak.invoicesystem.helpers.RestCommuncationHelper.getResponseString;

@Stateless
public class UserVehiclesService
{
	public List<SimpleUserVehicle> getVehicleChassisForUser(long userId, Date from, Date to){
		try {
			String url = "http://localhost:8080/UserSystem/v1/usersystem/users/";

			if(System.getenv("environment") != null && System.getenv("environment").equals("production")) {
				url = "http://userSystem:8080/deploy/v1/usersystem/users/";
			}

			url += userId+"/vehicles/from/"+from.getTime()+"/to/"+to.getTime();

			System.out.println(url);

			return (List<SimpleUserVehicle>) callUrlAndCastResultMethode(
					(new TypeLiteral<List<SimpleUserVehicle>>(){}).getType(),
					url, "GET");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();

	}
	private Object callUrlAndCastResultMethode(Type type, String url, String requestMethod) throws IOException {
		String stringResult = getResponseString(url, requestMethod);

		if (stringResult != null) {
			return new Gson().fromJson(stringResult, type);
		} else {
			return null;
		}
	}
}
