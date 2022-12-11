package tech.reliab.course.mennibayevat.bank.service;

import tech.reliab.course.mennibayevat.bank.entity.CreditAccount;
import tech.reliab.course.mennibayevat.bank.entity.Employee;
import tech.reliab.course.mennibayevat.bank.entity.PaymentAccount;
import tech.reliab.course.mennibayevat.bank.entity.User;

import java.time.LocalDate;

public interface CreditAccountService {
    CreditAccount create(User user, String bankName,
                         LocalDate creditBegin, LocalDate creditEnd,
                         Long creditAmount, Long mouthPayment,
                         Employee employee, PaymentAccount paymentAccount);
    CreditAccount getCreditAccount();
    void update(CreditAccount creditAccount);
    void delete(CreditAccount creditAccount);
}
