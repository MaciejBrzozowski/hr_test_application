package pl.brzozowski.maciej.clis.utilities;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import pl.brzozowski.maciej.clis.entity.User;

import java.util.Base64;
import java.util.Date;

@AllArgsConstructor
public class TokenGenerator {

    private TokenDetails tokenDetails;
    private Gson gson;
    private Base64.Encoder base64;
    private final int VALID_FOR_MINUTS = 30;

    public TokenGenerator() {
        gson = new Gson();
        base64 = Base64.getEncoder();
    }

    public TokenGenerator(User user) {
        gson = new Gson();
        base64 = Base64.getEncoder();
        tokenDetails = new TokenDetails(user.getEmail(), new Date().getTime());
    }

    public String getToken() {
        String gsonString = gson.toJson(this.tokenDetails);
        return base64.encodeToString(gsonString.getBytes());
    }

    public String getToken(User user) {
        this.tokenDetails = new TokenDetails(user.getEmail(), new Date().getTime());
        return getToken();
    }

    public User updateTokenForUser(User user) {
        user.getUserDetails().setToken(getToken(user));
        return user;
    }

    public boolean isTokenValid(String token) {
        String tokenDetailsJson = new String(Base64.getDecoder().decode(token));
        tokenDetails = new Gson().fromJson(tokenDetailsJson, TokenDetails.class);
        return tokenDetails.isTokenValid(VALID_FOR_MINUTS);
    }
}
