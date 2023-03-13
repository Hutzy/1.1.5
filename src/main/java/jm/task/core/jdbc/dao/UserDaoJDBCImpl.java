package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS user (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                                                            "name VARCHAR(100) NOT NULL, " +
                                                            "lastName VARCHAR(100) NOT NULL, " +
                                                            "age TINYINT(3) NOT NULL)";
        try  (Connection connection = Util.connection();
              Statement statement = connection.createStatement()) {
              statement.execute(sqlCreate);
        }
        catch (SQLException e) {
            System.out.println("Ошибка SQL1" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Нет драйвера");
        }
    }

    public void dropUsersTable() {
        String sqldrop = "DROP TABLE IF EXISTS user";
        try  (Connection connection = Util.connection();
              Statement statement = connection.createStatement()) {
              statement.execute(sqldrop);
        }
        catch (SQLException e) {
            System.out.println("Ошибка SQL2" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Нет драйвера");
        }
    }

    //"INSERT INTO users(name, lastName, age) VALUES (%s, %s, %d)".formatted(name, lastName, age);
    //"INSERT INTO user (name, lastName, age) VALUES (" + name + " , " + lastName + " , " + age + ")";

    public void saveUser(String name, String lastName, byte age) {
        String sqlSave = "INSERT INTO user (name, lastName, age) VALUES (?,?,?)";
        try  (Connection connection = Util.connection();
              PreparedStatement statement = connection.prepareStatement(sqlSave)) {
              statement.setString(1, name);
              statement.setString(2, lastName);
              statement.setByte(3, age);
              statement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Ошибка SQL3" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Нет драйвера");
        }
    }

    public void removeUserById(long id) {
        String sqlRemove = "DELETE FROM user WHERE id = " + id;
        try  (Connection connection = Util.connection();
              Statement statement = connection.createStatement()) {
              statement.execute(sqlRemove);
        }
        catch (SQLException e) {
            System.out.println("Ошибка SQL4" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Нет драйвера");
        }
    }

    public List<User> getAllUsers() {
        List <User> users = new ArrayList<>();
        String sqlGetAll = "SELECT * FROM user";
        try  (Connection connection = Util.connection();
              Statement statement = connection.createStatement()) {
              ResultSet set = statement.executeQuery(sqlGetAll);
              while(set.next()) {
                  User resUser = new User();
                  resUser.setId(set.getLong("id"));
                  resUser.setName(set.getString("name"));
                  resUser.setLastName(set.getString("lastName"));
                  resUser.setAge(set.getByte("age"));
                  users.add(resUser);
              }
        }
        catch (SQLException e) {
            System.out.println("Ошибка SQL5" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Нет драйвера");
        }
        return users;
    }

    public void cleanUsersTable() {
        String sqlСlean = "TRUNCATE user";
        try  (Connection connection = Util.connection();
              Statement statement = connection.createStatement()) {
              statement.execute(sqlСlean);
        }
        catch (SQLException e) {
            System.out.println("Ошибка SQL6" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Нет драйвера");
        }
    }
}
