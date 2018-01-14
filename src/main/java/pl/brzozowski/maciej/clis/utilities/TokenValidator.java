package pl.brzozowski.maciej.clis.utilities;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

public class TokenValidator {
    private String token;
    @Autowired
    private TokenDetails tokenDetails;
    @Autowired
    private Logger logger;

    public boolean validateTokenForUser(HttpServletRequest request) {
        boolean isTokenValid = false;
        this.token = request.getHeader("token");
        logger.info(token);
        return tokenDetails.isTokenValid(token, 30);
    }

}
