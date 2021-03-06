package pl.brzozowski.maciej.clis.entity;

public class User {

    private String email;
    private String password;
    private String token;
    private UserDetails userDetails;

    public User() {
    }

    public User(String email, String password, UserDetails userDetails) {
        this.email = email;
        this.password = password;
        this.userDetails = userDetails;
    }

    public User(UserIn userIn) {
        this.email = userIn.getEmail();
        this.password = userIn.getPassword();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User setUserEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + this.email + '\'' +
                ", password= *" +
                ", token='" + this.token + '\'' +
                '}';
    }
}
