package com.proftaak.usersystem.dao;

import com.proftaak.usersystem.models.ClientUser;

public interface UserDao {
    boolean saveUserInformation(String name, String email, String address, String residence);
    ClientUser getClientUserByName(String name);
    ClientUser getClientUserById(int id);
}
