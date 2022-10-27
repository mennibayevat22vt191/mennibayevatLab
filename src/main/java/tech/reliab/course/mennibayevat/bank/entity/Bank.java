package tech.reliab.course.mennibayevat.bank.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
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
