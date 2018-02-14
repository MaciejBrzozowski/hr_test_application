package pl.brzozowski.maciej.clis.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pl.brzozowski.maciej.clis.services.PreHandleAuthTokenService;
import pl.brzozowski.maciej.clis.services.UserHistoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class ApplicationHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private PreHandleAuthTokenService preHandleAuthTokenService;

    @Autowired
    private UserHistoryService userHistoryService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return preHandleAuthTokenService.preHandleIfTokenIsValid(httpServletRequest, httpServletResponse, o);
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        userHistoryService.save(httpServletRequest, httpServletResponse);
    }
}
