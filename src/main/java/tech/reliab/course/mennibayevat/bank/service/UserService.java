package tech.reliab.course.mennibayevat.bank.service;

import tech.reliab.course.mennibayevat.bank.entity.Bank;
import tech.reliab.course.mennibayevat.bank.entity.PaymentAccount;
import tech.reliab.course.mennibayevat.bank.entity.User;

public interface UserService {
    User create(String fullName, String workPlace, Bank bank);

    User getUserById(Long id);

    PaymentAccount getRandomPaymentAccount(User user);

    PaymentAccount getRandomPaymentAccount(User user, String bankName);

    void update(User user);

    void delete(User user);

    String userAccountsInfo(User user);
}
