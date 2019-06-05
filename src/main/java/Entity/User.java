package Entity;

public class User {
    private String login;
    private String password;
    private String authorizationstatus;
    private int id;

    public User() {}

    public User(String login, String password, String authorizationstatus, int id) {
        this.login = login;
        this.password = password;
        this.authorizationstatus = authorizationstatus;
        this.id = id;
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

    public String getAuthorizationstatus() {
        return authorizationstatus;
    }

    public void setAuthorizationstatus(String authorizationstatus) {
        this.authorizationstatus = authorizationstatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", authorizationstatus='" + authorizationstatus + '\'' +
                ", id=" + id +
                '}';
    }
}
