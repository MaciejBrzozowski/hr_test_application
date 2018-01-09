package pl.brzozowski.maciej.clis.repository;

import lombok.NoArgsConstructor;
import pl.brzozowski.maciej.clis.entity.User;

import java.util.HashMap;

@NoArgsConstructor
public class UserRepository {


    private HashMap<String, User> repository = new HashMap<>();

    UserRepository(HashMap<String, User> repository) {
        this.repository = repository;
    }

    public User read(String email) {
        return repository.get(email);
    }

    public User save(User user) {
        if (user.getEmail() != null) {
            repository.put(user.getEmail(), user);
        }
        return read(user.getEmail());
    }

    public User update(User user) {
        User userRepository = read(user.getEmail());
        userRepository = user;
        save(user);
        return repository.get(user.getEmail());
    }

    public int delete(User user) {
        repository.remove(user.getEmail());
        return 0;
    }

}