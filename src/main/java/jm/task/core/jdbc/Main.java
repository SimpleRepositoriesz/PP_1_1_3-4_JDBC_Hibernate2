package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        // Создание таблицы
        userService.createUsersTable();

        // Добавление пользователей
        userService.saveUser("John", "Doe", (byte) 25);
        userService.saveUser("Jane", "Doe", (byte) 30);
        userService.saveUser("Alice", "Smith", (byte) 22);
        userService.saveUser("Bob", "Johnson", (byte) 35);

        // Получение всех пользователей и вывод в консоль
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

        // Очистка таблицы
        userService.cleanUsersTable();

        // Удаление таблицы
        userService.dropUsersTable();
    }
}