package tech.reliab.course.mennibayevat.bank.service;

import tech.reliab.course.mennibayevat.bank.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;

public interface CreditAccountService {
    CreditAccount create(User user, String bankName,
                         LocalDate creditBegin, LocalDate creditEnd,
                         Long creditAmount, Long mouthPayment,
                         Employee employee, PaymentAccount paymentAccount);
//    ArrayList<CreditAccount> getCreditAccountsByUser(User user);
//    ArrayList<CreditAccount> getCreditAccountsByBank(Bank bank);
    void update(CreditAccount creditAccount);
    void delete(CreditAccount creditAccount);
}
