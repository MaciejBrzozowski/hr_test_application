package pl.brzozowski.maciej.clis.entity;

import org.springframework.lang.Nullable;

public class UserOut {

    private String email;
    private String token;

    public UserOut() {
    }

    public UserOut(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public UserOut(User user) {
        this.email = user.getEmail();
        this.token = user.getToken();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(@Nullable String token) {
        this.token = token;
    }
}
