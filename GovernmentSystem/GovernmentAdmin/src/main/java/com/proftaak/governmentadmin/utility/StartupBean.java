//package com.proftaak.governmentadmin.utility;
//
//import dao.KweetDao;
//import dao.UserDao;
//import model.*;
//
//import javax.annotation.PostConstruct;
//import javax.ejb.Singleton;
//import javax.ejb.Startup;
//import javax.inject.Inject;
//import java.io.UnsupportedEncodingException;
//import java.security.NoSuchAlgorithmException;
//
//@Startup
//@Singleton
//public class StartupBean {
//    @Inject
//    UserDao userDao;
//
//    @Inject
//    KweetDao kweetDao;
//
//
//    @PostConstruct
//    public void init() {
//        User adminUser = null;
//        try {
//            adminUser = new User("admin", AuthenticationUtils.encodeSHA256("admin"));
//            adminUser.setActivated(true);
//            userDao.addUser(adminUser, new Group(adminUser.getUsername(), Group.ADMINISTRATORS_GROUP));
//
//
//            User user1 = new User("rick", AuthenticationUtils.encodeSHA256("rick"));
//            Profile profile = new Profile("Rick Jeurissen", "Eindhoven", "www.kaas.nl", "nvt");
//            user1.setActivated(true);
//            user1.setProfile(profile);
//
//            User user2 = new User("stan", AuthenticationUtils.encodeSHA256("stan"));
//            Profile profile2 = new Profile("Stan Jeurissen", "Uden", "www.kaas.nl", "nvt");
//            user2.setActivated(true);
//            user2.setProfile(profile2);
//
//            User user3 = new User("deborah", AuthenticationUtils.encodeSHA256("deborah"));
//            Profile profile3 = new Profile("Deborah Jeurissen", "Nijmegen", "www.kaas.nl", "nvt");
//            user3.setActivated(true);
//            user3.setProfile(profile3);
//
//            userDao.addUser(user1, new Group(user1.getUsername(), Group.USERS_GROUP));
//            userDao.addUser(user2, new Group(user2.getUsername(), Group.USERS_GROUP));
//            userDao.addUser(user3, new Group(user3.getUsername(), Group.USERS_GROUP));
//
//            user1 = userDao.findByUsername("rick");
//            user2 = userDao.findByUsername("stan");
//            user3 = userDao.findByUsername("deborah");
//
//
//            kweetDao.addKweet(new Kweet(new ProfileInfo("Rick Jeurissen", user1.getProfile().getId()), "Ik heb net pindakaas gegeten!"));
//            kweetDao.addKweet(new Kweet(new ProfileInfo("Rick Jeurissen", user1.getProfile().getId()), "Feeling lonely on this platform..."));
//
//            kweetDao.addKweet(new Kweet(new ProfileInfo("Stan Jeurissen", user2.getProfile().getId()), "Ik wil kaas!"));
//
//            kweetDao.addKweet(new Kweet(new ProfileInfo("Deborah Jeurissen", user3.getProfile().getId()), "Ja ik kweet het."));
//
//
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//    }
//}
