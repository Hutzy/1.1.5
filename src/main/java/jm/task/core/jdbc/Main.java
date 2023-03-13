package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userService = new UserDaoJDBCImpl();

        userService.createUsersTable();

        userService.saveUser("Name1", "lastName1", (byte) 12);
        userService.saveUser("Name2", "lastName2", (byte) 26);
        userService.saveUser("Name3", "lastName3", (byte) 99);
        userService.saveUser("Name4", "lastName4", (byte) 0);

        userService.getAllUsers().forEach(x -> System.out.println("User с именем - " + x.getName() + " Добавлен в консоль"));

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
