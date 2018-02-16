package pl.brzozowski.maciej.clis.utilities;

import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;
import java.util.Date;
import java.util.logging.Logger;

@NoArgsConstructor
public class TokenDetails {

    private String userEmail;
    private long timestamp;
    @Autowired
    private Logger logger;

    TokenDetails(Logger logger) {
        this.logger = logger;
    }

    public TokenDetails(String userEmail, long timestamp) {
        this.userEmail = userEmail;
        this.timestamp = timestamp;
    }

    public TokenDetails(String userEmail) {
        this.userEmail = userEmail;
        timestamp = new Date().getTime();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isTokenValid(int validForMinuts) {
        return (new Date().getTime() - this.timestamp + validForMinuts * 6000) < 0;
    }

    public boolean isTokenValid(String token, int validForMinuts) {
        boolean isBase64 = token.getBytes().length % 4 == 0;
        boolean isTokenValid = false;
        if (isBase64) {
            TokenDetails tokenDetails = extractTokenDetails(token);
            this.timestamp = tokenDetails.timestamp;
            this.userEmail = tokenDetails.userEmail;
            long tokenValidForTime = (new Date().getTime() - this.timestamp + validForMinuts * 6000);
            logger.info("Token valid for nanoseconds :" + tokenValidForTime);
            isTokenValid = tokenValidForTime > 0;
        }
        return isTokenValid;
    }

    public TokenDetails extractTokenDetails(String token) {
        boolean isBase64 = token.getBytes().length % 4 == 0;
        if (isBase64) {
            String unbasedToken = new String(Base64.getDecoder().decode(token));
            logger.info("Token after unbase :" + unbasedToken);
            return new Gson().fromJson(unbasedToken, TokenDetails.class);
        } else {
            logger.info("Token invalid");
            return new TokenDetails();
        }
    }
}
