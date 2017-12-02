package pl.brzozowski.maciej.clis.web.controller.Register;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.brzozowski.maciej.clis.web.TemplatesDefinitions.IndexTemplate;


@Controller
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
        return "index";
    }

}
