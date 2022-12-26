package tech.reliab.course.mennibayevat.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class PaymentAccount {
    private Long id;
    @ToString.Exclude
    @JsonIgnore
    private User user;
    private String bank;
    private Integer moneyAmount;
}
