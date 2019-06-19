package com.proftaak.driverapplication.dao;

import com.proftaak.driverapplication.models.LoginAttempt;

import javax.persistence.EntityManager;
import java.util.List;

public interface LoginAttemptDao
{
	LoginAttempt getById(long id);
	List<LoginAttempt> getByUserId(long userId);
	List<LoginAttempt> getAll();
	LoginAttempt add(LoginAttempt loginAttempt);
	void setEm(EntityManager em);
	EntityManager getEm();
}
