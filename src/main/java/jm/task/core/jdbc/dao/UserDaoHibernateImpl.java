package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<User> query = session.createSQLQuery("create table if not exists  users " +
                    "(id int primary key auto_increment," +
                    "name varchar(45)," +
                    "lastname varchar(45)," +
                    "age tinyint)");
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица users создана");
        } catch (Exception e) {
            sessionFactory.getCurrentSession().beginTransaction().rollback();
            System.out.println("Таблица не может быть создана");

        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            Query<User> query = session.createSQLQuery("DROP TABLE if exists `users`.`users`");
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица users удалена");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Нельзя удалить таблицу");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.getCurrentSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("User не может быть добавлен");
        }


    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("User с таким id не существует");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            list = query.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table is clean!");
        } catch (Exception e) {
            System.out.println("Таблица не может быть очищена");
        }
    }
}
