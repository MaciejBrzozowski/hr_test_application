package pl.brzozowski.maciej.clis.utilities;

import org.apache.commons.lang3.RandomStringUtils;
import pl.brzozowski.maciej.clis.entity.User;
import pl.brzozowski.maciej.clis.entity.UserDetails;

public class ResetTokenGenerator implements GeneratorInterface {

    private int tokenLenght = 128;

    @Override
    public String generateNewToken() {
        return RandomStringUtils.randomAlphanumeric(tokenLenght);
    }

    @Override
    public String generateNewToken(User user) {
        if (user.getUserDetails() == null) {
            user.setUserDetails(new UserDetails());
        }
        user.getUserDetails().setResetPasswordValue(generateNewToken());
        return user.getUserDetails().getResetPasswordValue();
    }

    @Override
    public User updateTokenForUser(User user) {
        generateNewToken(user);
        return user;
    }
}
