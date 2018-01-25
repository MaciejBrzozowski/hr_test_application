package pl.brzozowski.maciej.clis.entity;


public class UserDetails {

    private String firstName;
    private String lastName;
    private String customData;
    private String resetPasswordValue;

    public UserDetails() {
    }

    public UserDetails(String firstName, String lastName, String customData) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customData = customData;
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

    public String getCustomData() {
        return customData;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }


    public String getResetPasswordValue() {
        return this.resetPasswordValue;
    }

    public void setResetPasswordValue(String resetPasswordValue) {
        this.resetPasswordValue = resetPasswordValue;
    }
}

