package pl.brzozowski.maciej.clis.controller.unathorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.brzozowski.maciej.clis.entity.UserIn;
import pl.brzozowski.maciej.clis.entity.UserOut;
import pl.brzozowski.maciej.clis.services.LoginService;

import static pl.brzozowski.maciej.clis.configuration.UrlMaping.LOGIN;

@RestController
@EnableWebSecurity
@RequestMapping(LOGIN)
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public UserOut login(@RequestBody UserIn userIn) {
        return loginService.loginUser(userIn);
    }
}
