package pl.brzozowski.maciej.clis.utilities;

import pl.brzozowski.maciej.clis.entity.User;

public interface GeneratorInterface {

    String generateNewToken();

    String generateNewToken(User user);

    User updateTokenForUser(User user);
}
