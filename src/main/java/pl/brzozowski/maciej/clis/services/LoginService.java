package pl.brzozowski.maciej.clis.services;

import org.springframework.beans.factory.annotation.Autowired;
import pl.brzozowski.maciej.clis.entity.User;
import pl.brzozowski.maciej.clis.entity.UserIn;
import pl.brzozowski.maciej.clis.entity.UserOut;
import pl.brzozowski.maciej.clis.repository.UserRepository;
import pl.brzozowski.maciej.clis.utilities.TokenGenerator;

public class LoginService {

    @Autowired
    private LoginService loginService;
    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private UserRepository userRepository;
    private User user;

    public UserOut generateTokenForUser(UserIn userIn) {
        user = userRepository.read(userIn.getEmail());


        return new UserOut(user);
    }
}
