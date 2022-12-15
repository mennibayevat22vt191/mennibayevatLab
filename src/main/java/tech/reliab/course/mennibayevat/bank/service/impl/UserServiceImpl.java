package tech.reliab.course.mennibayevat.bank.service.impl;

import lombok.AllArgsConstructor;
import tech.reliab.course.mennibayevat.bank.entity.Bank;
import tech.reliab.course.mennibayevat.bank.entity.CreditAccount;
import tech.reliab.course.mennibayevat.bank.entity.PaymentAccount;
import tech.reliab.course.mennibayevat.bank.entity.User;
import tech.reliab.course.mennibayevat.bank.repository.UserRepository;
import tech.reliab.course.mennibayevat.bank.service.BankService;
import tech.reliab.course.mennibayevat.bank.service.UserService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private static Long id = 0L;
    private UserRepository userRepository;
    private BankService bankService;

    @Override
    public User create(String fullName,
                       String workPlace,
                       Bank bank) {

        Random random = new Random();
        var userIncome = random.nextInt(10_000);
        List<PaymentAccount> paymentAccounts = new ArrayList<>();
        List<CreditAccount> creditAccounts = new ArrayList<>();
        List<Bank> banks = new ArrayList<>();
        var user = new User()
                .setId(id++)
                .setFullName(fullName)
                .setBirthday(Date.from(Instant.now()))
                .setWorkPlace(workPlace)
                .setMonthlyIncome(userIncome)
                .setBanks(banks)
                .setRate(userIncome / 10)
                .setPaymentAccounts(paymentAccounts)
                .setCreditAccounts(creditAccounts);

        userRepository.addEntity(user);
        bankService.addClient(bank);

        return user;
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.getById(id);
    }

    @Override
    public PaymentAccount getRandomPaymentAccount(User user) {
        //var random = new Random();
        return user.getPaymentAccounts()
                .stream()
                .findFirst()
                .orElseThrow();
    }

    @Override
    public void update(User user) {

        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        user.getBanks()
                .forEach(bank -> bankService.deleteClient(bank));
        userRepository.delete(user);
    }

    @Override
    public String userAccountsInfo(User user) {
        return "\nПользователь " + user.getFullName() + "\nПлатёжные аккаунты: \n" + user.paymentAccountsInfo() + "\nКредитные аккаунты: \n" + user.creditAccountsInfo();
    }
}
