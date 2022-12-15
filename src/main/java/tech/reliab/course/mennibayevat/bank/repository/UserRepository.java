package tech.reliab.course.mennibayevat.bank.repository;

import lombok.Getter;
import tech.reliab.course.mennibayevat.bank.entity.User;

import java.util.ArrayList;
import java.util.Objects;

@Getter
public class UserRepository implements Repository<User> {

    private final ArrayList<User> entities = new ArrayList<>();

    public void addEntity(User entity) {
        this.entities.add(entity);
    }

    public void delete(User entity) {
        for (User user : this.entities)
            if (Objects.equals(user, entity)) {
                this.entities.set(Math.toIntExact(user.getId()), null);
                break;
            }
    }

    public void save(User entity) {
        for (User user : this.entities)
            if (Objects.equals(user, entity)) {
                this.entities.set(Math.toIntExact(user.getId()), user);
                break;
            }
    }

    public User getById(Long id) {
        for (User user : this.entities) {
            if (user.getId().equals(id))
                return user;
        }
        return null;
    }
}
