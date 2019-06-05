package Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDao {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/base_indexsite?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() {
         try {
        Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
            return con;
        } catch (Exception ex) {
            System.out.println("Не удалось установить подключение к базе данных -->" + ex.getMessage());
            return null;
        }
    }
    public static void close(Connection con) {
        try  {
            con.close();
        }
        catch(Exception ex) {
            System.out.println("Не удалось закрыть подключение к базе данных -->" + ex.getMessage());
        }
    }
}
