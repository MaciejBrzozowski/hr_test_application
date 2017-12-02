package pl.brzozowski.maciej.clis.web.Register;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegisterController {

    @GetMapping("/register")
    public String registerController() {
        return "<h1>register</h1>";
    }

}
