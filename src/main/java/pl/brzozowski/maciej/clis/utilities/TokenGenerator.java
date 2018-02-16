package pl.brzozowski.maciej.clis.utilities;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import pl.brzozowski.maciej.clis.entity.User;

import java.util.Base64;
import java.util.Date;
import java.util.logging.Logger;

import static lombok.AccessLevel.PACKAGE;

@AllArgsConstructor(access = PACKAGE)
public class TokenGenerator implements GeneratorInterface {

    @Autowired
    private Logger logger;
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

    public String generateNewToken() {
        String gsonString = gson.toJson(this.tokenDetails);
        logger.info("token object: " + gsonString);
        return base64.encodeToString(gsonString.getBytes());
    }

    public String generateNewToken(User user) {
        tokenDetails = new TokenDetails(user.getEmail(), new Date().getTime());
        String gsonString = gson.toJson(this.tokenDetails);
        logger.info("token object: " + gsonString);
        return base64.encodeToString(gsonString.getBytes());
    }

    public User updateTokenForUser(User user) {
        user.setToken(generateNewToken(user));
        return user;
    }

    public boolean isTokenValid(String token) {
        boolean isTokenValid = false;
        if (token != null) {
            String tokenDetailsJson = new String(Base64.getDecoder().decode(token));
            tokenDetails = new Gson().fromJson(tokenDetailsJson, TokenDetails.class);
            isTokenValid = tokenDetails.isTokenValid(VALID_FOR_MINUTS);
        }
        return isTokenValid;
    }
}
