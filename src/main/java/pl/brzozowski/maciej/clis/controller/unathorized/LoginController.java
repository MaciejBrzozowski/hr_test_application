package pl.brzozowski.maciej.clis.controller.unathorized;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.brzozowski.maciej.clis.entity.UserIn;

import static pl.brzozowski.maciej.clis.configuration.UrlMaping.LOGIN;
import static pl.brzozowski.maciej.clis.configuration.UrlMaping.LOGIN_HTML_PAGE;

@Controller
@EnableWebSecurity
@RequestMapping(LOGIN)
public class LoginController {


    @GetMapping
    public String login(UserIn userIn) {

        return "index";
    }

    @PostMapping
    public String postLogin(HttpServletRequest request) {
        return "index";
    }


    @GetMapping(LOGIN_HTML_PAGE)
    public String loginHtml(@RequestBody UserIn userIn) {

        return "index";
    }

    @PostMapping(LOGIN_HTML_PAGE)
    public String postLoginHtml(HttpServletRequest request) {
        return "index";
    }

}
