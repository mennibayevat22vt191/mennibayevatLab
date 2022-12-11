package tech.reliab.course.mennibayevat.bank.service.impl;

import lombok.AllArgsConstructor;
import tech.reliab.course.mennibayevat.bank.entity.CreditAccount;
import tech.reliab.course.mennibayevat.bank.entity.Employee;
import tech.reliab.course.mennibayevat.bank.entity.PaymentAccount;
import tech.reliab.course.mennibayevat.bank.entity.User;
import tech.reliab.course.mennibayevat.bank.repository.BankRepository;
import tech.reliab.course.mennibayevat.bank.repository.CreditAccountRepository;
import tech.reliab.course.mennibayevat.bank.service.CreditAccountService;

import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
public class CreditAccountServiceImpl implements CreditAccountService {
    private CreditAccountRepository creditAccountRepository;
    private BankRepository bankRepository;

    private static Long id = 0L;

    @Override
    public CreditAccount create(User user, String bankName,
                                LocalDate creditBegin, LocalDate creditEnd,
                                Long creditAmount, Long mouthPayment,
                                Employee employee, PaymentAccount paymentAccount) {
        var bank = bankRepository.getByName(bankName);
        var account = new CreditAccount()
                .setId(id++)
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

        creditAccountRepository.save(account);

        return account;
    }

    @Override
    public CreditAccount getCreditAccount() {

        return creditAccountRepository.getEntity();
    }

    @Override
    public void update(CreditAccount creditAccount) {

        creditAccountRepository.save(creditAccount);
    }

    @Override
    public void delete(CreditAccount creditAccount) {

        creditAccountRepository.delete(creditAccount);
    }
}
