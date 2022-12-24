package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Misha", "Smirnov", (byte) 20);
        userService.saveUser("Dima", "Ivanov", (byte) 21);
        userService.saveUser("Dasha", "Sidorova", (byte) 22);
        userService.saveUser("Vasya", "Pypkin", (byte) 23);
        userService.getAllUsers().stream().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}