package pl.brzozowski.maciej.clis.entity;

public class User {

    private String email;
    private String password;
    private UserDetails userDetails;

    public User() {
    }

    public User(String email, String password, UserDetails userDetails) {
        this.email = email;
        this.password = password;
        this.userDetails = userDetails;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
