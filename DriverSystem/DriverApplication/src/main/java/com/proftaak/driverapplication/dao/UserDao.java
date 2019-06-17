package com.proftaak.driverapplication.dao;

import com.proftaak.driverapplication.models.DriverUser;

import javax.persistence.EntityManager;
import java.util.List;

public interface UserDao {
    DriverUser saveNewUser(String username, String password);
	DriverUser getDriverUserById(long id);
	List<DriverUser> getAll();
	DriverUser verifyUser(String username, String password);
	void setEm(EntityManager em);
	EntityManager getEm();
}
