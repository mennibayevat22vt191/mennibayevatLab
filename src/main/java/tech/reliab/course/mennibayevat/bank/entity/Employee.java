package tech.reliab.course.mennibayevat.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class Employee {
    private Long id;
    private String fullName;
    private Date birthday;
    private String post;
    private Bank bank;
    private Boolean isRemotely;
    private BankOffice office;
    private Boolean isMakeLoan;
    private Integer salary;
}
