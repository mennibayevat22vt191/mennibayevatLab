package tech.reliab.course.mennibayevat.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CreditAccount {
    private Long id;
    @ToString.Exclude
    private User user;
    private String bankName;
    private LocalDate creditStart;
    private LocalDate creditEnd;
    private Integer loanPeriod;
    private Long loanAmount;
    private Long monthlyPayment;
    private Integer interestRate;
    private Employee creditor;
    @ToString.Exclude
    private PaymentAccount paymentAccount;
}
