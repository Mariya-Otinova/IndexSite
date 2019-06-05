package Dao;

import Dao.ConnectDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Entity.*;

public class IndexbotDao {

    private String getIdSite(User user, Site site) {
        SiteDao siteDao = new SiteDao();
        return siteDao.getIdSite(user, site);
    }

    public String addStatistics(User user, Site site, Indexbot indexbot) {
        String REPORT;
        if (getIdSite(user, site).contains("Идентификация сайта прошла успешно!")) {
        try {
            Connection connection = ConnectDao.getConnection();
            String SQL = "INSERT INTO base_indexsite.dynamic_index (id, site_id, user_id, created, uniqwords, words, latin_words, cyrillic_words, freq_wordlist, freq_letter) VALUES (NULL, ?, ?, NULL, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, site.getId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.setInt(3, indexbot.getUniqwords());
            preparedStatement.setInt(4, indexbot.getWords());
            preparedStatement.setInt(5, indexbot.getLatinWords());
            preparedStatement.setInt(6, indexbot.getCyrillicWords());
            preparedStatement.setString(7, indexbot.getFreqWordList());
            preparedStatement.setString(8, indexbot.getFreqLetter());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            REPORT = "Добавление статистики сайта прошло успешно";
        } catch (Exception ex) {
            System.out.println("Не удалось сохранить статистику сайта -->" + ex.getMessage());
            REPORT = "Не удалось сохранить статистику сайта по техническим причинам";
        }
        } else {
            REPORT = "Нельзя вычислить статистику сайта, который отсутствует в списке";
        }
        return REPORT;
    }

    public ArrayList<ModelStat> viewStatistics(User user) {
        ArrayList<ModelStat> STAT = new ArrayList<ModelStat>();
        try {
            Connection connection = ConnectDao.getConnection();
            String SQL = "SELECT s.created + INTERVAL EXTRACT(HOUR FROM TIMEDIFF(UTC_TIMESTAMP(),NOW())) HOUR AS created, di.created + INTERVAL EXTRACT(HOUR FROM TIMEDIFF(UTC_TIMESTAMP(),NOW())) HOUR AS upcreated, s.url AS url, di.uniqwords AS uniqwords, di.words AS words, di.latin_words AS latinwords, \n" +
                    "  di.cyrillic_words AS cyrillicwords, di.freq_wordlist AS freqwordlist, di.freq_letter AS freqletter\n" +
                    "FROM (SELECT * FROM base_indexsite.sitelist s WHERE s.user_id=? AND s.deletflag=\"0\") s LEFT OUTER JOIN\n" +
                    "(SELECT * FROM (SELECT * FROM base_indexsite.dynamic_index di WHERE di.user_id=? ORDER BY di.created DESC) di GROUP BY di.site_id) di\n" +
                    "ON s.id=di.site_id";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ModelStat modelStat = new ModelStat();
                modelStat.setTmsCreated(resultSet.getTimestamp("created"));
                modelStat.setTmsLastIndex(resultSet.getTimestamp("upcreated"));
                modelStat.setUrl(resultSet.getString("url"));
                modelStat.setUniqwords(resultSet.getInt("uniqwords"));
                modelStat.setWords(resultSet.getInt("words"));
                modelStat.setLatinWords(resultSet.getInt("latinwords"));
                modelStat.setCyrillicWords(resultSet.getInt("cyrillicwords"));
                modelStat.setFreqWordList(resultSet.getString("freqwordlist"));
                modelStat.setFreqLetter(resultSet.getString("freqletter"));
                STAT.add(modelStat);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println("Не удалось извлечь статистику -->" + ex.getMessage());
        } return STAT;
    }

}
