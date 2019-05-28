package com.proftaak.driverapplication.utility;

import com.proftaak.driverapplication.dao.UserDao;
import com.proftaak.driverapplication.models.DriverUser;

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
        DriverUser user = null;
        try {
            userDao.saveNewUser("driveruser" , AuthenticationUtils.encodeSHA256(" driveruser" ));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
