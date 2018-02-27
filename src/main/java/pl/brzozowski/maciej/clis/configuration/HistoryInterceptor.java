package pl.brzozowski.maciej.clis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Component
public class HistoryInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private Logger logger;

    private String requestBody;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpServletRequest requestCacheWrapperObject = new ContentCachingRequestWrapper(request);
        requestCacheWrapperObject.getParameterMap();
        ServletInputStream servletInputStream = requestCacheWrapperObject.getInputStream();
        int contentLenght = request.getContentLength();
        byte[] buffer = new byte[contentLenght];
        servletInputStream.read(buffer);
        requestBody = new String(buffer);
        logger.info("Request body: " + requestBody);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

}
