package tech.reliab.course.mennibayevat.bank.service.impl;

import lombok.AllArgsConstructor;
import tech.reliab.course.mennibayevat.bank.entity.Bank;
import tech.reliab.course.mennibayevat.bank.entity.CreditAccount;
import tech.reliab.course.mennibayevat.bank.entity.PaymentAccount;
import tech.reliab.course.mennibayevat.bank.entity.User;
import tech.reliab.course.mennibayevat.bank.repository.EmployeeRepository;
import tech.reliab.course.mennibayevat.bank.repository.UserRepository;
import tech.reliab.course.mennibayevat.bank.service.BankService;
import tech.reliab.course.mennibayevat.bank.service.CreditAccountService;
import tech.reliab.course.mennibayevat.bank.service.PaymentAccountService;
import tech.reliab.course.mennibayevat.bank.service.UserService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;
    private BankService bankService;
    private PaymentAccountService paymentAccountService;
    private CreditAccountService creditAccountService;
    private static Long id = 0L;


    @Override
    public User create(String fullName,
                       String workPlace,
                       Bank bank) {

        Random random = new Random();
        var userIncome = random.nextInt(10_000);
        final ArrayList<PaymentAccount> Paccounts = new ArrayList<>();
        final ArrayList<CreditAccount> Caccounts = new ArrayList<>();
        var user = new User()
                .setId(id++)
                .setFullName(fullName)
                .setBirthday(Date.from(Instant.now()))
                .setWorkPlace(workPlace)
                .setMonthlyIncome(userIncome)
                .setBanks(bank)
                .setRate(userIncome / 10)
                .setPaymentAccounts(Paccounts)
                .setCreditAccounts(Caccounts);

        userRepository.addEntity(user);
        bankService.addClient(bank);

        return user;
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.getById(id);
    }

    @Override
    public void update(User user) {

        userRepository.save(user);
    }

    @Override
    public void delete(User user) {

        userRepository.delete(user);
        bankService.deleteClient(user.getBanks());
    }
}
