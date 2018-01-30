package pl.brzozowski.maciej.clis.controller.unathorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import pl.brzozowski.maciej.clis.entity.UserIn;
import pl.brzozowski.maciej.clis.entity.UserOut;
import pl.brzozowski.maciej.clis.exceptions.UserNotExistsException;
import pl.brzozowski.maciej.clis.services.LoginService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @ExceptionHandler({UserNotExistsException.class})
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }
}
