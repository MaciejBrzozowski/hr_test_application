package pl.brzozowski.maciej.clis.configuration;

public class UrlMaping {

    public static final String BASE_UNIT_CONVERSION_URL = "/auth/convert";
    public static final String UNIT_URL = "/{unitIn}/to/{unitOut}";
    public static final String UNIT_CONVERSION_QUANTITY = "/quantity/{quantity:.+}";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String RESET = "/reset";
    public static final String TAV = "/token/{token}";
    public static final String USER_HISTORY = "/auth/history";

}
