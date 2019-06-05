package Service;

import Dao.SiteDao;
import Entity.*;

public class SiteService {


    public String addSite(User user, Site site) {
        String NOTICE;
        if (user.getAuthorizationstatus()=="authorized") {
            SiteDao siteDao = new SiteDao();
            NOTICE = siteDao.addSite(user, site);
        } else {
            NOTICE = "Для добавления сайта, необходимо авторизоваться";
        }
        return NOTICE;
    }

    public String deleteSite(User user, Site site) {
        String NOTICE;
        if (user.getAuthorizationstatus()=="authorized") {
            SiteDao siteDao = new SiteDao();
            NOTICE = siteDao.deleteSite(user, site);
        } else {
            NOTICE = "Для удаления сайта, необходимо авторизоваться";
        }
        return NOTICE;
    }

}
