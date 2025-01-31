package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // Параметры для JDBC подключения
    private static final String URL = "jdbc:mysql://localhost:3306/sys";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "roott";

    // SessionFactory для Hibernate
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Создаем SessionFactory на основе конфигурации Hibernate
            sessionFactory = new Configuration()
                    .setProperty("hibernate.connection.url", URL)
                    .setProperty("hibernate.connection.username", USERNAME)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
                    .setProperty("hibernate.show_sql", "true")

                    .addAnnotatedClass(User.class) // Регистрируем класс User
                    .buildSessionFactory();
            System.out.println("SessionFactory создан успешно.");
        } catch (Throwable ex) {
            System.err.println("Ошибка при создании SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Метод для получения SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Метод для закрытия SessionFactory
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            System.out.println("SessionFactory закрыт.");
        }
    }

    // Метод для JDBC
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение с базой данных установлено.");
        } catch (SQLException e) {
            System.err.println("Ошибка при подключении к базе данных: " + e.getMessage());
        }
        return connection;
    }
}