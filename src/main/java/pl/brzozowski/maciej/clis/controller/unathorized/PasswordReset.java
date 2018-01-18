package pl.brzozowski.maciej.clis.controller.unathorized;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableWebSecurity
public class PasswordReset {

    @GetMapping("/reset")
    public String resetPassword() {

        return "";
    }


}
