package pl.brzozowski.maciej.clis.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brzozowski.maciej.clis.entity.User;
import pl.brzozowski.maciej.clis.entity.UserIn;
import pl.brzozowski.maciej.clis.entity.UserOut;
import pl.brzozowski.maciej.clis.exceptions.UserAlreadyExistsException;
import pl.brzozowski.maciej.clis.repository.UserRepository;

import static lombok.AccessLevel.PACKAGE;
import static pl.brzozowski.maciej.clis.ClisApplication.LOG;

@Service
@AllArgsConstructor(access = PACKAGE)
@NoArgsConstructor
public class RegisterService {

    @Autowired
    private UserRepository userRepository;
    private User user;

    public UserOut registerUserInDatabase(UserIn userIn) {
        if (userRepository.read(new User(userIn)) != null) {
            throw new UserAlreadyExistsException("User " + userIn.getEmail() + " already exists");
        }
        LOG.info("user registered " + userIn.getEmail());
        user = new User(userIn);
        userRepository.save(user);
        LOG.info("user registered ", user.toString());
        return new UserOut(user);
    }

}
