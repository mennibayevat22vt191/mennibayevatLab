package tech.reliab.course.mennibayevat.bank.entity;

import java.util.Date;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class User {
    private Long id;
    private String fullName;
    private Date birthday;
    private String workPlace;
    private Integer monthlyIncome;
    private Bank banks;
    @ToString.Exclude
    private CreditAccount creditAccounts;
    @ToString.Exclude
    private PaymentAccount paymentAccounts;
    private Integer rate;
}
