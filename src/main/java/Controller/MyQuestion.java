package Controller;

public class MyQuestion {
    private String action;
    private String login;
    private String password;
    private String newpassword;
    private String url;

    public MyQuestion() {
    }

    public MyQuestion(String action, String login, String password, String newpassword, String url) {
        this.action = action;
        this.login = login;
        this.password = password;
        this.newpassword = newpassword;
        this.url = url;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MyQuestion{" +
                "action='" + action + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", newpassword='" + newpassword + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
