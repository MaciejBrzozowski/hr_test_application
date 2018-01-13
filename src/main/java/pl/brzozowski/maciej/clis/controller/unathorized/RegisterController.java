package pl.brzozowski.maciej.clis.controller.unathorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.brzozowski.maciej.clis.entity.User;
import pl.brzozowski.maciej.clis.entity.UserIn;
import pl.brzozowski.maciej.clis.repository.UserRepository;

import static org.springframework.http.HttpStatus.CREATED;
import static pl.brzozowski.maciej.clis.ClisApplication.LOG;


@RestController
public class RegisterController {

    @Autowired
    private UserRepository userRepository;
    private String info = "Use put mapping to add new user";
    private User user;

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
    public void putRegisterController(@RequestBody UserIn userIn) {

        if (userIn != null) {
            LOG.info("user registered " + user.getEmail());
            user = new User(userIn);
            userRepository.save(user);
            LOG.info("user registered ", user.toString());
        }

    }


}
