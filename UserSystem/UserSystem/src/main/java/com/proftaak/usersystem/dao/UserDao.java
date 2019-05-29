package com.proftaak.usersystem.dao;

import com.proftaak.usersystem.models.ClientUser;

import javax.ws.rs.client.Client;
import com.proftaak.usersystem.models.UserVehicle;
import java.util.List;

public interface UserDao {
    ClientUser saveUserInformation(String name, String email, String address, String residence);
    ClientUser getClientUserByName(String name);
    ClientUser getClientUserById(int id);
	List<ClientUser> getAll();
	ClientUser editUser(ClientUser user);
	UserVehicle getUserVehicle(long vehicleId, long userId);
}
