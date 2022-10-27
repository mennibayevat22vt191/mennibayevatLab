package tech.reliab.course.mennibayevat.bank.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CreditAccount {
    private Long id;
    private User user;
    private String bankName;
    private Date creditStart;
    private Date creditEnd;
    private Integer loanPeriod;
    private Long loanAmount;
    private Long monthlyPayment;
    private Integer interestRate;
    private Employee creditor;
    private PaymentAccount paymentAccount;
}
