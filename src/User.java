public class User {
    private String login;
    private String name;
    private String password;

    public User(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public boolean isTruePassword(String password) {
        return this.password.equals(password) ? true : false;
    }

    public boolean authentication(String inputLogin, String inputPassword) {
        if (inputLogin == login && inputPassword == password) {
            return true;
        } else {
            return false;
        }
    }
}
