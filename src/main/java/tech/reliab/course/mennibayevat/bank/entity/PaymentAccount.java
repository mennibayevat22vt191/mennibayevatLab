package tech.reliab.course.mennibayevat.bank.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class PaymentAccount {
    private Long id;
    private User user;
    private String bank;
    private Integer moneyAmount;
}
