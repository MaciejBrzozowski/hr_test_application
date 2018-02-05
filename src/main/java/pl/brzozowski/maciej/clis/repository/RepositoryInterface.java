package pl.brzozowski.maciej.clis.repository;

public interface RepositoryInterface<T, E> {

    T read(E element);

    T save(E element);

    T update(E element);

    int delete(E element);

}
