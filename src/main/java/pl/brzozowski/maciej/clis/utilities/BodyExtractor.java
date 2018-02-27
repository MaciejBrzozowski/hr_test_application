package pl.brzozowski.maciej.clis.utilities;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.logging.Logger;


public class BodyExtractor {

    @Autowired
    private Logger logger;

    public String getBody(HttpServletRequest httpServletRequest) {
        String body = "";


        return body;
    }

    public String getBody(HttpServletResponse httpServletResponse) {
        String body = "";

        return body;
    }

}
