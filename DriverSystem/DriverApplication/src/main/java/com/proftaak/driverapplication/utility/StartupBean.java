package com.proftaak.driverapplication.utility;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class StartupBean {
//    @Inject
//    UserDao userDao;

    @PostConstruct
    public void init() {
//        DriverUser user = null;
//        try {
//            userDao.saveNewUser("driveruser" , AuthenticationUtils.encodeSHA256("driveruser" ));
//        } catch (UnsupportedEncodingException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
    }
}
