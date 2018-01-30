package pl.brzozowski.maciej.clis.controller.unathorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import pl.brzozowski.maciej.clis.entity.User;
import pl.brzozowski.maciej.clis.entity.UserIn;
import pl.brzozowski.maciej.clis.exceptions.UserNotExistsException;
import pl.brzozowski.maciej.clis.repository.UserRepository;
import pl.brzozowski.maciej.clis.services.EmailSenderService;
import pl.brzozowski.maciej.clis.utilities.EmailTemplate;
import pl.brzozowski.maciej.clis.utilities.ResetTokenGenerator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static pl.brzozowski.maciej.clis.configuration.UrlMaping.RESET;
import static pl.brzozowski.maciej.clis.configuration.UrlMaping.TAV;

@RestController
@EnableWebSecurity
@RequestMapping(RESET)
public class PasswordResetController {

    @Autowired
    private Environment environment;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResetTokenGenerator resetTokenGenerator;
    @Autowired
    private EmailSenderService emailSenderService;
    private User user;
    private String title = "Password reset for test web app";
    private String sender = "Reset_password_service";
    private String link;

    @ResponseStatus(value = NO_CONTENT)
    @PostMapping
    public void resetPassword(@RequestBody UserIn userIn) {
        user = checkUser(userIn);
        link = "localhost:" + environment.getProperty("server.port") + RESET + "/token/" + resetTokenGenerator.generateNewToken(user);
        emailSenderService.setTitle(title);
        emailSenderService.setSender(sender);
        emailSenderService.setMessage(EmailTemplate.resetPasswordEmail.replace("{$link}", link));
        emailSenderService.sendStandardEmail(user.getEmail());
    }

    @ResponseStatus(value = NO_CONTENT)
    @PostMapping(TAV)
    public void resetPasswordWithToken(@PathVariable("token") String token, @RequestBody UserIn userIn) {
        User user = checkUser(userIn);
        if (user.getUserDetails().getResetPasswordValue().contentEquals(token)) {
            user.setPassword(userIn.getPassword());
            user.setToken(null);
            userRepository.save(user);
        }
    }

    private User checkUser(UserIn userIn) {
        User user = userRepository.read(userIn.getEmail());
        if (user == null) {
            throw new UserNotExistsException("User " + userIn.getEmail() + " is not registered");
        }
        return user;
    }

    @ExceptionHandler({UserNotExistsException.class})
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }
}
