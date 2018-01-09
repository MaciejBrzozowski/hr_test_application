package pl.brzozowski.maciej.clis.entity;


public class UserDetails {

    private String firstName;
    private String lastName;
    private String avatar;
    private String token;

    public UserDetails() {
    }

    public UserDetails(String firstName, String lastName, String avatar, String token) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

