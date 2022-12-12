package tech.reliab.course.mennibayevat.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class Bank {
    private Long id;
    private String name;
    private Integer officeCount;
    private Integer atmCount;
    private Integer employeeCount;
    private Integer clientCount;
    private Integer rate;
    private Long moneyStock;
    private Integer interestRate;
}
