package techkids.vn.android7pomodoro.settings;

/**
 * Created by minhh on 14/01/2017.
 */

public class LoginCredentials {
    private String username;
    private String password;
    private String accsessToken;

    public LoginCredentials(String username, String password, String accsessToken) {
        this.username = username;
        this.password = password;
        this.accsessToken = accsessToken;
    }

    public String getAccsessToken() {
        return accsessToken;
    }

    public void setAccsessToken(String accsessToken) {
        this.accsessToken = accsessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginCredentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accsessToken='" + accsessToken + '\'' +
                '}';
    }
}
