package pl.brzozowski.maciej.clis.utilities;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Base64;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class TokenDetails {

    private String userEmail;
    private long timestamp;

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
        String unbasedToken = String.valueOf(Base64.getDecoder().decode(token));
        TokenDetails tokenDetails = new Gson().fromJson(unbasedToken, TokenDetails.class);
        this.timestamp = tokenDetails.timestamp;
        this.userEmail = tokenDetails.userEmail;
        return (new Date().getTime() - this.timestamp + validForMinuts * 6000) < 0;
    }


}
