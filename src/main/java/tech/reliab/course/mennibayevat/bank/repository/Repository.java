package tech.reliab.course.mennibayevat.bank.repository;

public interface Repository<T> {
    void save(T entity);
    void delete(T entity);
}
