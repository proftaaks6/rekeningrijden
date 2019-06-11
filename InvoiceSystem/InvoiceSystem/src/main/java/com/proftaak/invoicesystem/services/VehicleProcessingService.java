package com.proftaak.invoicesystem.services;

import com.proftaak.invoicesystem.dao.UserProcessingDao;
import com.proftaak.invoicesystem.models.UserProcessingState;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Stateless
@Default
public class VehicleProcessingService {

    @Inject
    private UserProcessingDao processingDao;

    public boolean addNewUser(long userId){
        return processingDao.addNewUser(userId);
    }

    public List<UserProcessingState> getAllUsers() {
        return processingDao.getAllUsers();
    }

    public UserProcessingState getUserById(long userId){return processingDao.getUserById(userId);}
}
