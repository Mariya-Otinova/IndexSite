package Dao;

import Dao.ConnectDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Entity.*;

public class UserDao {

    public String registration(User user) {
        String MESSAGE;
        try {
            Connection connection = ConnectDao.getConnection();
            String SQL = "INSERT INTO base_indexsite.userlist (id, created, upcreated, login, password) VALUE (NULL, NOW(), NULL,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            MESSAGE = "Регистрация прошла успешно!";
        } catch (Exception ex) {
            System.out.println("Не удалось зарегистрировать пользователя -->" + ex.getMessage());
            if (ex.getMessage().contains("Duplicate")) {
                MESSAGE = "Пользователь с таким именем уже существует";
            } else {
                MESSAGE = "Регистрация не удалась по техническим причинам, попробуйте позже";
            }
        } return MESSAGE;
    }

    public String authorization(User user) {
        String MESSAGE;
        try {
            Connection connection = ConnectDao.getConnection();
            String SQL = "SELECT id FROM base_indexsite.userlist WHERE login=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            int LOGIN = 0;
            while (resultSet.next()) {
                LOGIN = resultSet.getInt("id");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            if ( LOGIN != 0 ) {
                user.setId(LOGIN);
                MESSAGE = "Авторизация прошла успешно!";
            } else {
                MESSAGE = "Пользователь с таким логином/паролем отсутствует";
            }

        } catch (Exception ex) {
            System.out.println("Не удалось авторизововать пользователя -->" + ex.getMessage());
            MESSAGE = "Не удалось авторизовать пользователя по техническим причинам";
        } return MESSAGE;
    }

    public String changePassword(User user) {
        String MESSAGE;
        try {
            Connection connection = ConnectDao.getConnection();
            String SQL = "UPDATE base_indexsite.userlist set password=? WHERE login=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            MESSAGE = "Смена пароля прошла успешно!";
        } catch (Exception ex) {
            System.out.println("Не удалось изменить пароль пользователя -->" + ex.getMessage());
            MESSAGE = "Не удалось изменить пароль пользователя по техническим причинам";
        } return MESSAGE;
    }
}
