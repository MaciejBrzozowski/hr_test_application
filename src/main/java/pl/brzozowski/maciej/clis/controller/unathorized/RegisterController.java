package pl.brzozowski.maciej.clis.controller.unathorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import pl.brzozowski.maciej.clis.entity.User;
import pl.brzozowski.maciej.clis.entity.UserIn;
import pl.brzozowski.maciej.clis.entity.UserOut;
import pl.brzozowski.maciej.clis.exceptions.UserAlreadyExistsException;
import pl.brzozowski.maciej.clis.services.RegisterService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.CREATED;
import static pl.brzozowski.maciej.clis.configuration.UrlMaping.REGISTER;


@RestController
@EnableWebSecurity
@RequestMapping(REGISTER)
public class RegisterController {

    static final String INFO = "Use put mapping to add new user";
    private User user;
    @Autowired
    private RegisterService registerService;

    @GetMapping
    public String registerController() {
        return INFO;
    }

    @PostMapping
    public String postRegisterController() {
        return INFO;
    }

    @PutMapping
    @ResponseStatus(value = CREATED)
    public UserOut putRegisterController(@RequestBody UserIn userIn) {
        return registerService.registerUserInDatabase(userIn);
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.ALREADY_REPORTED.value());
    }
}
