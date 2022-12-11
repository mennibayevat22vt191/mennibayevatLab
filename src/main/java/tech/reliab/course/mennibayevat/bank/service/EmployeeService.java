package tech.reliab.course.mennibayevat.bank.service;

import tech.reliab.course.mennibayevat.bank.entity.Bank;
import tech.reliab.course.mennibayevat.bank.entity.BankOffice;
import tech.reliab.course.mennibayevat.bank.entity.Employee;

public interface EmployeeService {
    Employee create(String fullName, String post, Bank bank, BankOffice office);
    Employee getEmployee();
    void update(Employee employee);
    void delete(Employee employee);
}
