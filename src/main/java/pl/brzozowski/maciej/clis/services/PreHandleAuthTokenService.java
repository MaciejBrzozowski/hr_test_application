package pl.brzozowski.maciej.clis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.brzozowski.maciej.clis.repository.UserRepository;
import pl.brzozowski.maciej.clis.utilities.TokenValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Component
public class PreHandleAuthTokenService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenValidator tokenValidator;
    @Autowired
    private Logger logger;

    public boolean preHandleIfTokenIsValid(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {
        String servletPath = httpServletRequest.getServletPath();
        logger.info("Servlet path :" + servletPath);
        boolean isPathForAuthorizedRequests = servletPath.startsWith("/auth");
        boolean isTokenValid = tokenValidator.validateTokenForUser(httpServletRequest, userRepository);
        logger.info("isPathForAuthorizedRequests : " + isPathForAuthorizedRequests);
        logger.info("isTokenValid :" + isTokenValid);
        return isPathForAuthorizedRequests == isTokenValid;
    }
}
