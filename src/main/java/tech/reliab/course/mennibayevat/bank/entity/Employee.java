package tech.reliab.course.mennibayevat.bank.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
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
