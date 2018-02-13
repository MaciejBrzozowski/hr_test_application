package pl.brzozowski.maciej.clis.utilities;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

import static java.util.Optional.ofNullable;

public class TokenValidator {
    private String token;
    @Autowired
    private TokenDetails tokenDetails;
    @Autowired
    private Logger logger;

    public boolean validateTokenForUser(HttpServletRequest request) {
        this.token = request.getHeader("token");
        logger.info("Token :" + token);
        if (ofNullable(token).isPresent()) {
            return tokenDetails.isTokenValid(token, 30);
        }
        return false;
    }

}
