package pl.brzozowski.maciej.clis.repository;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.brzozowski.maciej.clis.entity.User;

import java.util.HashMap;

@Scope(value = "singleton")
@Component
@NoArgsConstructor
public class UserRepository implements RepositoryInterface<User, User> {


    private HashMap<String, User> repository = new HashMap<>();

    UserRepository(HashMap<String, User> repository) {
        this.repository = repository;
    }

    public User read(User user) {
        return repository.get(user.getEmail());
    }

    public User save(User user) {
        if (user.getEmail() != null) {
            return repository.put(user.getEmail(), user);
        }
        return null;
    }

    public User update(User user) {
        if (read(user).getEmail() != null) {
            save(user);
            return repository.get(user.getEmail());
        }
        return null;
    }

    public int delete(User user) {
        repository.remove(user.getEmail());
        return 0;
    }

}
