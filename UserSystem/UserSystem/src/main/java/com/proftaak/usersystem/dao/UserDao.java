package com.proftaak.usersystem.dao;

import com.proftaak.usersystem.models.ClientUser;
import java.util.List;

public interface UserDao {
    ClientUser saveUserInformation(String name, String email, String address, String residence);
    ClientUser getClientUserByName(String name);
    ClientUser getClientUserById(int id);
	List<ClientUser> getAll();
}
