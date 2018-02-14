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
        TokenDetails tokenDetails = extractTokenDetails(token);
        this.timestamp = tokenDetails.timestamp;
        this.userEmail = tokenDetails.userEmail;
        long tokenValidForTime = (new Date().getTime() - this.timestamp + validForMinuts * 6000);
        logger.info("Token valid for nanoseconds :" + tokenValidForTime);
        return tokenValidForTime > 0;
    }

    public TokenDetails extractTokenDetails(String token) {
        boolean isBase64 = org.apache.commons.codec.binary.Base64.isBase64(token.getBytes());
        if (isBase64) {
            String unbasedToken = new String(Base64.getDecoder().decode(token));
            logger.info("token after unbase :" + unbasedToken);
            return new Gson().fromJson(unbasedToken, TokenDetails.class);
        } else {
            return new TokenDetails();
        }
    }
}
