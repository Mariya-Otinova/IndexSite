package Dao;

import Dao.ConnectDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Entity.*;

public class SiteDao {

    public String getIdSite(User user, Site site) {
        String NOTICE;
        try {
            Connection connection = ConnectDao.getConnection();
            String SQL = "SELECT id FROM base_indexsite.sitelist WHERE user_id=? AND url=? AND deletflag=\"0\";";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, site.getUrl());
            ResultSet resultSet = preparedStatement.executeQuery();
            int ID = 0;
            while (resultSet.next()) {
                ID = resultSet.getInt("id");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            if ( ID != 0 ) {
                site.setId(ID);
                NOTICE = "Идентификация сайта прошла успешно!";
            } else {
                NOTICE = "Сайт с таким адресом отсутствует, добавьте его в список";
            }
        } catch (Exception ex) {
            System.out.println("Не удалось идентифицировать сайт -->" + ex.getMessage());
            NOTICE = "Не удалось идентифицировать сайт по техническим причинам";
        } return NOTICE;
    }

    public String addSite(User user, Site site) {
        String NOTICE;
        if (getIdSite(user, site).contains("Сайт с таким адресом отсутствует, добавьте его в список")) {
        try {
            Connection connection = ConnectDao.getConnection();
            String SQL = "INSERT INTO base_indexsite.sitelist (id, user_id, created, upcreated, url) VALUE  (NULL, ?, NOW(), NULL, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, site.getUrl());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            NOTICE = "Сайт успешно добавлен в список";
        } catch (Exception ex) {
            System.out.println("Не удалось добавить сайт в список -->" + ex.getMessage());
            NOTICE = "Не удалось добавить сайт в список по техническим причинам";
        }
        } else {
            NOTICE = "Нельзя дважды добавить сайт в лист";
        } return NOTICE;
    }

    public String deleteSite(User user, Site site) {
        String NOTICE;
        try {
            Connection connection = ConnectDao.getConnection();
            String SQL = "UPDATE base_indexsite.sitelist SET deletflag=\"1\" WHERE user_id=? AND url=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, site.getUrl());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            NOTICE = "Сайт успешно удалён из списка";
        } catch (Exception ex) {
            System.out.println("Не удалось удалить сайт из списка -->" + ex.getMessage());
            NOTICE = "Не удалось добавить сайт в список по техническим причинам";
        } return NOTICE;
    }

}
