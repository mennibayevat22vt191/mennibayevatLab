package tech.reliab.course.mennibayevat.bank.service.impl;

import lombok.AllArgsConstructor;
import tech.reliab.course.mennibayevat.bank.entity.PaymentAccount;
import tech.reliab.course.mennibayevat.bank.entity.User;
import tech.reliab.course.mennibayevat.bank.repository.PaymentAccountRepository;
import tech.reliab.course.mennibayevat.bank.service.PaymentAccountService;

import java.util.Random;

@AllArgsConstructor
public class PaymentAccountServiceImpl implements PaymentAccountService {
    private PaymentAccountRepository paymentAccountRepository;

    private static Long id = 0L;

    @Override
    public PaymentAccount create(User user, String bankName) {
        Random random = new Random();
        var account = new PaymentAccount()
                .setId(++id)
                .setUser(user)
                .setBank(bankName)
                .setMoneyAmount(random.nextInt(100_000));
        paymentAccountRepository.save(account);

        return account;
    }

    @Override
    public PaymentAccount getPaymentAccount() {

        return paymentAccountRepository.getEntity();
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
