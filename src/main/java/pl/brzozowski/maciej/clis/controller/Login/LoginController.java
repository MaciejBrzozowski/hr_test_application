package pl.brzozowski.maciej.clis.controller.Login;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.brzozowski.maciej.clis.ClisApplication;
import pl.brzozowski.maciej.clis.utilities.IndexTemplate;

@Controller
@EnableWebSecurity
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(ClisApplication.class);

    private final String LOGIN = "/login";
    private IndexTemplate index;

    public LoginController(IndexTemplate index) {
        this.index = index;
    }

    @GetMapping(LOGIN)
    public String login(Model model) {
        model.addAttribute("title", index.LOGIN_HEADER);
        model.addAttribute("page_title", index.LOGIN_PAGE_TITLE);
        model.addAttribute("redirect_register", index.REGISTER_REDIRECT);
        model.addAttribute("redirect_reset", index.RESET_REDIRECT);
        model.addAttribute("register_text", index.REGISTER_TEXT);
        model.addAttribute("reset_text", index.RESET_TEXT);
        model.addAttribute("submitText", index.LOGIN_SUBMIT);
        model.addAttribute("form_name", index.LOGIN_FORM_NAME);
        model.addAttribute("action", "/login");
        return "index";
    }

    @PostMapping(LOGIN)
    public String postLogin(HttpServletRequest request) {
        log.info("User login request : ", request.getParameter("Email"));
        return "index";
    }

}
