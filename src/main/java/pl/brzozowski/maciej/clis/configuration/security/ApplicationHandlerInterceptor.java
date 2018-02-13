package pl.brzozowski.maciej.clis.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pl.brzozowski.maciej.clis.services.UserHistoryService;
import pl.brzozowski.maciej.clis.utilities.TokenValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

import static pl.brzozowski.maciej.clis.configuration.UrlMaping.*;

@Configuration
public class ApplicationHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenValidator tokenValidator;
    @Autowired
    private Logger logger;
    @Autowired
    private UserHistoryService userHistoryService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String servletPath = httpServletRequest.getServletPath();
        logger.info("Servlet path :" + servletPath);
        boolean isPathForAuthorizedRequests = servletPath.contains("auth");
        boolean isTokenValid = tokenValidator.validateTokenForUser(httpServletRequest);
        boolean isPathIsForRegister = (servletPath.endsWith(REGISTER)) |
                (servletPath.endsWith(LOGIN)) |
                (servletPath.endsWith(RESET));
        boolean preHandleRequest = (isPathForAuthorizedRequests & isTokenValid) | isPathIsForRegister;
        logger.info("isPathForAuthorizedRequests : " + isPathForAuthorizedRequests);
        logger.info("isTokenValid :" + isTokenValid);
        logger.info("isPathIsForRegister :" + isPathIsForRegister);
        logger.info("Prehandle result: " + preHandleRequest);
        return preHandleRequest;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        userHistoryService.save(httpServletRequest, httpServletResponse);
    }
}
