package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;


public class Main {
    public static void main(String[] args) {


        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();


        userDao.saveUser("123", "123", (byte) 1);
        userDao.saveUser("234", "234", (byte) 2);
        userDao.saveUser("345", "345", (byte) 3);
        userDao.saveUser("456", "456", (byte) 4);

//        System.out.println(userDao.getAllUsers());
        userDao.removeUserById(1);
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
