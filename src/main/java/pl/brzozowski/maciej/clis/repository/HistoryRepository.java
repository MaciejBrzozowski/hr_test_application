package pl.brzozowski.maciej.clis.repository;

import org.springframework.stereotype.Component;
import pl.brzozowski.maciej.clis.entity.UserHistory;

import java.util.HashMap;
import java.util.LinkedList;

import static java.util.Optional.ofNullable;

@Component
public class HistoryRepository implements RepositoryInterface<LinkedList<String>, UserHistory> {

    private HashMap<String, LinkedList<String>> repository = new HashMap<>();

    HistoryRepository(HashMap<String, LinkedList<String>> repository) {
        this.repository = repository;
    }

    @Override
    public LinkedList<String> read(UserHistory element) {
        LinkedList<String> read = repository.get(element.getEmail());
        return ofNullable(read).orElse(new LinkedList<>());
    }

    @Override
    public LinkedList<String> save(UserHistory element) {
        LinkedList list = repository.get(element.getEmail());
        if (ofNullable(list).isPresent()) {
            if (repository.size() > 100) {
                list.pop();
            }
            list.addLast(element.getQuery());
        } else {
            list = new LinkedList<>();
            list.add(element.getQuery());
            repository.put(element.getEmail(), list);
        }

        return read(element);
    }

    @Override
    public LinkedList<String> update(UserHistory element) {
        return read(element);
    }

    @Override
    public int delete(UserHistory element) {
        repository.get(element.getEmail()).clear();
        return 0;
    }
}
