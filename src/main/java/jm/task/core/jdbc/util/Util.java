package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/new_mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Gegrby2030@";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            //  Загружаем driver MySQL JDBC
            Class.forName(DRIVER);
            // устанавливаю соединение
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
