package pl.brzozowski.maciej.clis.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import pl.brzozowski.maciej.clis.entity.User;
import pl.brzozowski.maciej.clis.repository.UserRepository;

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

    public boolean validateTokenForUser(HttpServletRequest request, UserRepository userRepository) {
        boolean isTokenValid = validateTokenForUser(request);
        boolean isTokenExistsInDb = false;
        if (isTokenValid) {
            String userEmail = tokenDetails.extractTokenDetails(token).getUserEmail();
            User user = userRepository.read(new User().setUserEmail(userEmail));
            isTokenExistsInDb = ofNullable(user).map(User::getToken).orElseGet(String::new).matches(token);
            logger.info("Token exists in database: " + isTokenExistsInDb);
        }
        return isTokenExistsInDb && isTokenValid;
    }

}
