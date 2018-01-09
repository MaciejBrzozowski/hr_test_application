package pl.brzozowski.maciej.clis.controller.PasswordReset;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@EnableWebSecurity
public class PasswordReset {

    @GetMapping("/reset")
    public String resetPassword() {

        return "index";
    }


}
