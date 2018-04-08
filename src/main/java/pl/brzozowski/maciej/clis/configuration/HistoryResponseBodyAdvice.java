package pl.brzozowski.maciej.clis.configuration;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import pl.brzozowski.maciej.clis.entity.UnitsOut;
import pl.brzozowski.maciej.clis.services.UserHistoryService;

import java.util.logging.Logger;

@RestControllerAdvice
public class HistoryResponseBodyAdvice implements ResponseBodyAdvice {

    @Autowired
    private Logger logger;
    private String requestPath;
    private String bodyOut;
    @Autowired
    private UserHistoryService userHistoryService;
    @Setter
    private static String bodyIn;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        requestPath = request.getURI().getRawPath();
        logger.info(requestPath);
        if ((body instanceof UnitsOut) || (body instanceof String)) {
            logger.info("BODY OUT: " + body.toString());
            bodyOut = body.toString();
        }
        userHistoryService.save(requestPath, bodyIn, bodyOut);
        bodyIn = null;
        return body;
    }
}
