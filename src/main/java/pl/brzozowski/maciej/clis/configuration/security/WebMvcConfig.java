package pl.brzozowski.maciej.clis.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.brzozowski.maciej.clis.configuration.HistoryInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ApplicationHandlerInterceptor applicationHandlerInterceptor;

    @Autowired
    private HistoryInterceptor historyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(applicationHandlerInterceptor).addPathPatterns("/auth/**");
        registry.addInterceptor(historyInterceptor).addPathPatterns("/auth/**");
    }
}
