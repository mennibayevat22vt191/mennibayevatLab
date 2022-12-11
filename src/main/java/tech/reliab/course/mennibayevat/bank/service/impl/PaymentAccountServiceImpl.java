package tech.reliab.course.mennibayevat.bank.service.impl;

import tech.reliab.course.mennibayevat.bank.entity.PaymentAccount;
import tech.reliab.course.mennibayevat.bank.entity.User;
import tech.reliab.course.mennibayevat.bank.service.PaymentAccountService;

import java.util.Random;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    //TODO private PaymentAccountRepository paymentAccountRepository;

    private static Long id = 0L;

    @Override
    public PaymentAccount create(User user, String bankName) {
        Random random = new Random();
        var account = new PaymentAccount()
                .setId(++id)
                .setUser(user)
                .setBank(bankName)
                .setMoneyAmount(random.nextInt(100_000));
        //TODO paymentAccountRepository.save(account);

        return account;
    }

    @Override
    public PaymentAccount getPaymentAccount() {

        //TODO return paymentAccountRepository.getEntity();
        return null;
    }

    @Override
    public void update(PaymentAccount paymentAccount) {

        //TODO paymentAccountRepository.save(paymentAccount);
    }

    @Override
    public void delete(PaymentAccount paymentAccount) {

        //TODO paymentAccountRepository.delete(paymentAccount);
    }
}
