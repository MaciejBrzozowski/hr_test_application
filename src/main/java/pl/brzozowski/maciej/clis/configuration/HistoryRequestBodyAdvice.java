package pl.brzozowski.maciej.clis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import pl.brzozowski.maciej.clis.entity.UnitsIn;

import java.lang.reflect.Type;
import java.util.logging.Logger;

@RestControllerAdvice
public class HistoryRequestBodyAdvice extends RequestBodyAdviceAdapter {

    @Autowired
    private Logger logger;

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if ((body instanceof UnitsIn) || (body instanceof String)) {
            logger.info("BODY IN: " + body.toString());
        }
        HistoryResponseBodyAdvice.setBodyIn(body.toString());
        return body;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

}
