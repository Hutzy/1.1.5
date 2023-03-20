package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS User (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(100) NOT NULL, " +
                "lastName VARCHAR(100) NOT NULL, " +
                "age TINYINT(3) NOT NULL)";
            try {
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
                transaction.commit();
                session.close();
            } catch (Exception e) {
                System.out.println("Ошибка при добавлении в БД");
                e.printStackTrace();
                //А код вообще хоть кто то смотрит?
                //Почему первую задачу приняли без транзакций? :(
            }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS User";
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Ошибка при удалении БД");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении пользователя в БД");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Ошибка при удалении пользователя из БД");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "FROM User";
        List<User> result = new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            result.addAll(session.createQuery(sql, User.class).getResultList());
            session.close();
        } catch (Exception e) {
            System.out.println("Ошибка при получении всех пользователей из БД");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE User";
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Ошибка при получении всех пользователей из БД");
            e.printStackTrace();
        }
    }
}
