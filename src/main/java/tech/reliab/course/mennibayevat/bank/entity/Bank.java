package tech.reliab.course.mennibayevat.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<BankOffice> offices;
    @JsonIgnore
    private List<BankAtm> atms;
    @JsonIgnore
    private List<Employee> employees;
    @JsonIgnore
    private List<User> clients;
    private Integer rate;
    private Long moneyStock;
    private Integer interestRate;
}
