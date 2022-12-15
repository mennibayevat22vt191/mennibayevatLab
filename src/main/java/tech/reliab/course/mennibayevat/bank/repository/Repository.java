package tech.reliab.course.mennibayevat.bank.repository;

public interface Repository<T> {
    void addEntity(T entity);

    void delete(T entity);

    void save(T entity);
}
