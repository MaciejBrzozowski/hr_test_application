package pl.brzozowski.maciej.clis.utilities;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class TokenValidator {
    private String token;
    @Autowired
    private TokenDetails tokenDetails;

    public boolean validateTokenForUser(HttpServletRequest request) {
        boolean isTokenValid = false;
        this.token = request.getHeader("token");
        return tokenDetails.isTokenValid(token, 30);
    }

}
