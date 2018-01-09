package pl.brzozowski.maciej.clis.controller.Login;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@EnableWebSecurity
public class LoginController {

    private final String LOGIN = "/login";

    @GetMapping(LOGIN)
    public String login(Model model) {

        return "index";
    }

    @PostMapping(LOGIN)
    public String postLogin(HttpServletRequest request) {
        return "index";
    }

}
