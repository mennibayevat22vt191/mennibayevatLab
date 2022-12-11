package tech.reliab.course.mennibayevat.bank.service;

import tech.reliab.course.mennibayevat.bank.entity.Bank;
import tech.reliab.course.mennibayevat.bank.entity.User;

public interface UserService {
    User create(String fullName, String workPlace, Bank bank);
    User getUser();
    void update(User user);
    void delete(User user);
}
