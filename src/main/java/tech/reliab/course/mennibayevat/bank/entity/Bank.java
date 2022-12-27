package tech.reliab.course.mennibayevat.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class Bank {
    private Long id;
    private String name;
    private List<BankOffice> offices;
    private List<BankAtm> atms;
    private List<Employee> employees;
    private List<User> clients;
    private Integer rate;
    private Long moneyStock;
    private Integer interestRate;
}
