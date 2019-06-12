package com.proftaak.invoicesystem.services;

import com.proftaak.invoicesystem.dao.RegionDao;
import com.proftaak.invoicesystem.dao.RegionPointDao;
import com.proftaak.invoicesystem.dao.UserProcessingDao;
import com.proftaak.invoicesystem.models.SquareRegion;
import com.proftaak.invoicesystem.models.UserProcessingState;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Default
public class UserProcessingService {

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
