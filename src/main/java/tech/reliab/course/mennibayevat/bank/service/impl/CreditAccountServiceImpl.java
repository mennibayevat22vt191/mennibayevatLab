package tech.reliab.course.mennibayevat.bank.service.impl;

import tech.reliab.course.mennibayevat.bank.entity.CreditAccount;
import tech.reliab.course.mennibayevat.bank.entity.Employee;
import tech.reliab.course.mennibayevat.bank.entity.PaymentAccount;
import tech.reliab.course.mennibayevat.bank.entity.User;
import tech.reliab.course.mennibayevat.bank.service.CreditAccountService;

import java.time.LocalDate;
import java.time.Period;

public class CreditAccountServiceImpl implements CreditAccountService {
    //TODO #1
    // private CreditAccountRepository creditAccountRepository;
    // private BankRepository bankRepository;

    private static Long idGenerator = 0L;

    @Override
    public CreditAccount create(User user, String bankName,
                                LocalDate creditBegin, LocalDate creditEnd,
                                Long creditAmount, Long mouthPayment,
                                Employee employee, PaymentAccount paymentAccount) {
        //TODO var bank = bankRepository.getByName(bankName);
        var account = new CreditAccount()
                .setId(idGenerator++)
                .setUser(user)
                .setBankName(bankName)
                .setCreditStart(creditBegin)
                .setCreditEnd(creditEnd)
                .setLoanPeriod(Period.between(creditBegin, creditEnd).getMonths())
                .setLoanAmount(creditAmount)
                .setMonthlyPayment(mouthPayment)
                .setInterestRate(bank.getInterestRate())
                .setCreditor(employee)
                .setPaymentAccount(paymentAccount);

        //TODO creditAccountRepository.save(account);

        return account;
    }

    @Override
    public CreditAccount getCreditAccount() {

        //TODO return creditAccountRepository.getEntity();
        return null;
    }

    @Override
    public void update(CreditAccount creditAccount) {

        //TODO creditAccountRepository.save(creditAccount);
    }

    @Override
    public void delete(CreditAccount creditAccount) {

        //TODO creditAccountRepository.delete(creditAccount);
    }
}
