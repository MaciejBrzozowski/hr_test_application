package pl.brzozowski.maciej.clis.web.TemplatesDefinitions;

import org.springframework.stereotype.Component;

@Component
public class IndexTemplate {

    public final String LOGIN_HEADER = "Login";
    public final String REGISTER_HEADER = "Register";
    public final String RESET_HEADER = "Reset password";
    public final String LOGIN_REDIRECT = "/login";
    public final String REGISTER_REDIRECT = "/register";
    public final String RESET_REDIRECT = "/reset";
    public final String LOGIN_PAGE_TITLE = "LOGIN";
    public final String REGISTER_PAGE_TITLE = "REGISTER";
    public final String RESET_PAGE_TITLE = "RESET PASSWORD";
    public final String LOGIN_TEXT = "Sign Up ->";
    public final String REGISTER_TEXT = "Sign in ->";
    public final String RESET_TEXT = "Forgot Password";
    public final String LOGIN_SUBMIT = "Log in";
    public final String REGISTER_SUBMIT = "Register";
    public final String RESET_SUBMIT = "Reset Password";


}
