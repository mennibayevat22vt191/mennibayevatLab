package tech.reliab.course.mennibayevat.bank.entity;

import java.util.Date;

import lombok.*;
import lombok.experimental.Accessors;

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
