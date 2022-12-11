package tech.reliab.course.mennibayevat.bank.entity;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class PaymentAccount {
    private Long id;
    private User user;
    private String bank;
    private Integer moneyAmount;
}
