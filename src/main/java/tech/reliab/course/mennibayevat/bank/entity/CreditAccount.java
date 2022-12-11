package tech.reliab.course.mennibayevat.bank.entity;

import java.time.LocalDate;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class CreditAccount {
    private Long id;
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
