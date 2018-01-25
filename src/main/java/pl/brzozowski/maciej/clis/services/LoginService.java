package pl.brzozowski.maciej.clis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brzozowski.maciej.clis.entity.User;
import pl.brzozowski.maciej.clis.entity.UserIn;
import pl.brzozowski.maciej.clis.entity.UserOut;
import pl.brzozowski.maciej.clis.repository.UserRepository;
import pl.brzozowski.maciej.clis.utilities.TokenGenerator;

import java.util.logging.Logger;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;
    private User user;
    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private Logger logger;

    public UserOut loginUser(UserIn userIn) {
        user = userRepository.read(userIn.getEmail());
        if ((user != null) && (user.getPassword().contentEquals(userIn.getPassword()))) {
            logger.info("User found in database: " + user.getEmail());
            user = tokenGenerator.updateTokenForUser(user);
            logger.info("New token generated: " + user.getToken());
            userRepository.save(user);
        }
        return new UserOut(user.getEmail(), user.getToken());
    }
}
