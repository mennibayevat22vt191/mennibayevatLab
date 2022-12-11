package tech.reliab.course.mennibayevat.bank.service;

import tech.reliab.course.mennibayevat.bank.entity.PaymentAccount;
import tech.reliab.course.mennibayevat.bank.entity.User;

public interface PaymentAccountService {
    PaymentAccount create(User user, String bankName);
    PaymentAccount getPaymentAccount();
    void update(PaymentAccount paymentAccount);
    void delete(PaymentAccount paymentAccount);
}
