package pl.brzozowski.maciej.clis.controller.Register;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.brzozowski.maciej.clis.utilities.IndexTemplate;


@Controller
@EnableWebSecurity
public class RegisterController {

    private IndexTemplate index;

    public RegisterController(IndexTemplate index) {
        this.index = index;
    }

    @GetMapping("/register")
    public String registerController(Model model) {
        model.addAttribute("title", index.REGISTER_HEADER);
        model.addAttribute("page_title", index.REGISTER_PAGE_TITLE);
        model.addAttribute("redirect_login", index.LOGIN_REDIRECT);
        model.addAttribute("login_text", index.LOGIN_TEXT);
        model.addAttribute("redirect_reset", index.RESET_REDIRECT);
        model.addAttribute("reset_text", index.RESET_TEXT);
        model.addAttribute("submitText", index.REGISTER_SUBMIT);
        model.addAttribute("form_name", index.REGISTER_FORM_NAME);
        return "index";
    }

    @PostMapping("/register")
    public String postRegisterController(Model model) {
        model.addAttribute("title", index.REGISTER_HEADER);
        model.addAttribute("page_title", index.REGISTER_PAGE_TITLE);
        model.addAttribute("redirect_login", index.LOGIN_REDIRECT);
        model.addAttribute("login_text", index.LOGIN_TEXT);
        model.addAttribute("redirect_reset", index.RESET_REDIRECT);
        model.addAttribute("reset_text", index.RESET_TEXT);
        model.addAttribute("submitText", index.REGISTER_SUBMIT);
        model.addAttribute("form_name", index.REGISTER_FORM_NAME);
        model.addAttribute("message", "You have bin PSI HUJU zarejsetrowany");
        return "index";
    }


}
