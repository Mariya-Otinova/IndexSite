package Service;

import Dao.IndexbotDao;
import Dao.SiteDao;
import Entity.*;

import java.util.ArrayList;

public class IndexbotService {

    private void rateStatistics(Indexbot indexbot, Site site) {
        IndexbotParser indexbotParser = new IndexbotParser();
        indexbotParser.rateStatistics(indexbot, site);
    }

    public String addStatistics(User user, Site site) {
        String REPORT;
        if (user.getAuthorizationstatus()=="authorized") {
            SiteDao siteDao = new SiteDao();
            Indexbot indexbot = new Indexbot();
            rateStatistics(indexbot, site);
            IndexbotDao indexbotdao = new IndexbotDao();
            REPORT = indexbotdao.addStatistics(user, site, indexbot);
        } else {
            REPORT = "Для добавления статистики сайта, необходимо авторизоваться";
        }
        return REPORT;
    }

    public ArrayList<ModelStat> viewStatistics(User user) {
        ArrayList<ModelStat> STAT = new ArrayList<ModelStat>();
        if (user.getAuthorizationstatus()=="authorized") {
            IndexbotDao indexbotdao = new IndexbotDao();
            STAT = indexbotdao.viewStatistics(user);
        }
        return STAT;
    }
}
