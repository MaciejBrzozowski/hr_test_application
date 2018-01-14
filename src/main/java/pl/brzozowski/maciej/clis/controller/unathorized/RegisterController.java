package pl.brzozowski.maciej.clis.controller.unathorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.brzozowski.maciej.clis.entity.User;
import pl.brzozowski.maciej.clis.entity.UserIn;
import pl.brzozowski.maciej.clis.exceptions.UserAlreadyExistsException;
import pl.brzozowski.maciej.clis.services.RegisterService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
public class RegisterController {


    private String info = "Use put mapping to add new user";
    private User user;
    @Autowired
    private RegisterService registerService;

    @GetMapping("/register")
    public String registerController() {
        return info;
    }

    @PostMapping("/register")
    public String postRegisterController() {
        return info;
    }

    @PutMapping("/register")
    @ResponseStatus(value = CREATED)
    public User putRegisterController(@RequestBody UserIn userIn) {
        return registerService.registerUserInDatabase(userIn);
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.ALREADY_REPORTED.value());
    }
}
