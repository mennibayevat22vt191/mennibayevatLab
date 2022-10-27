package tech.reliab.course.mennibayevat.bank.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class User {
    private Long id;
    private String fullName;
    private Date birthday;
    private String workPlace;
    private Integer monthlyIncome;
    private Bank banks;
    private CreditAccount creditAccounts;
    private PaymentAccount paymentAccounts;
    private Integer rate;
}
