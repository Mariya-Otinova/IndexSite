package Service;

import Dao.UserDao;
import Entity.*;

public class UserService {

    public String registration(User user) {
        UserDao userDao = new UserDao();
        String MESSAGE = userDao.registration(user);
        if (MESSAGE.contains("Регистрация прошла успешно!")) {
            userDao.authorization(user);
            user.setAuthorizationstatus("authorized");
        } else {
            user.setAuthorizationstatus("deauthorized");
        } return MESSAGE;
    }

    public String authorization(User user) {
        UserDao userDao = new UserDao();
        String MESSAGE = userDao.authorization(user);
        if (MESSAGE.contains("Авторизация прошла успешно!")) {
            user.setAuthorizationstatus("authorized");
        } else {
            user.setAuthorizationstatus("deauthorized");
        } return MESSAGE;
    }

    public String changePassword(User user, String newPassword) {
        UserDao userDao = new UserDao();
        String MESSAGE = userDao.authorization(user);
        if (MESSAGE.contains("Авторизация прошла успешно!")) {
            user.setPassword(newPassword);
            MESSAGE = userDao.changePassword(user);
            user.setAuthorizationstatus("authorized");
        }
        return MESSAGE;
    }

    public void deauthorization(User user) {
        user.setAuthorizationstatus("deauthorized");
    }
}
