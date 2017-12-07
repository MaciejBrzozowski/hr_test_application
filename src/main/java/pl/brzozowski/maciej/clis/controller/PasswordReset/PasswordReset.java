package pl.brzozowski.maciej.clis.controller.PasswordReset;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.brzozowski.maciej.clis.utilities.IndexTemplate;

@Controller
public class PasswordReset {


    private IndexTemplate index;

    public PasswordReset(IndexTemplate index) {
        this.index = index;
    }

    @GetMapping("/reset")
    public String resetPassword(Model model) {
        model.addAttribute("title", index.RESET_HEADER);
        model.addAttribute("page_title", index.RESET_PAGE_TITLE);
        model.addAttribute("redirect_register", index.REGISTER_REDIRECT);
        model.addAttribute("redirect_login", index.LOGIN_REDIRECT);
        model.addAttribute("register_text", index.REGISTER_TEXT);
        model.addAttribute("login_text", index.LOGIN_TEXT);
        model.addAttribute("submitText", index.RESET_SUBMIT);
        return "index";
    }


}
