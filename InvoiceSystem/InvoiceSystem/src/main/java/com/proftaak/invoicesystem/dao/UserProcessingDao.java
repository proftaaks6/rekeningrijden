package com.proftaak.invoicesystem.dao;


import com.proftaak.invoicesystem.models.UserProcessingState;

import java.util.List;

public interface UserProcessingDao {
    boolean addNewUser(long userId);
    List<UserProcessingState> getAllUsers();
    UserProcessingState getUserById(long userId);
}
