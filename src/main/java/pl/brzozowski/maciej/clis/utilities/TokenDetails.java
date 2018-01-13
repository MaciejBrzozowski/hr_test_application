package pl.brzozowski.maciej.clis.utilities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
}
