package com.proftaak.governmentadmin.utility;

import com.proftaak.governmentadmin.dao.UserDao;
import com.proftaak.governmentadmin.models.GovernmentEmployee;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Startup
@Singleton
public class StartupBean {
    @Inject
    UserDao userDao;



    @PostConstruct
    public void init() {
        GovernmentEmployee user = null;
        try {
            user = new GovernmentEmployee("governmentemployee", AuthenticationUtils.encodeSHA256("governmentemployee"), new ArrayList<>());
            userDao.addUser(user);


        } catch (UnsupportedEncodingException e) {

        } catch (NoSuchAlgorithmException e) {

        }


    }
}
