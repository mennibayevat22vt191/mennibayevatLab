package tech.reliab.course.mennibayevat.bank.service.impl;

import lombok.AllArgsConstructor;
import tech.reliab.course.mennibayevat.bank.entity.PaymentAccount;
import tech.reliab.course.mennibayevat.bank.entity.User;
import tech.reliab.course.mennibayevat.bank.repository.PaymentAccountRepository;
import tech.reliab.course.mennibayevat.bank.service.BankService;
import tech.reliab.course.mennibayevat.bank.service.PaymentAccountService;

import java.util.Random;

@AllArgsConstructor
public class PaymentAccountServiceImpl implements PaymentAccountService {
    private static Long id = 0L;
    private PaymentAccountRepository paymentAccountRepository;
    private BankService bankService;

    @Override
    public PaymentAccount create(User user, String bankName) {
        Random random = new Random();
        var account = new PaymentAccount()
                .setId(id++)
                .setUser(user)
                .setBank(bankName)
                .setMoneyAmount(random.nextInt(100_000));
        paymentAccountRepository.addEntity(account);
        user.getPaymentAccounts().add(account);
        if (!(user.getBanks().contains(bankService.getByName(bankName)))) {
            user.getBanks().add(bankService.getByName(bankName));
        }
        return account;
    }

    @Override
    public void update(PaymentAccount paymentAccount) {

        paymentAccountRepository.save(paymentAccount);
    }

    @Override
    public void delete(PaymentAccount paymentAccount) {

        paymentAccountRepository.delete(paymentAccount);
    }

}
