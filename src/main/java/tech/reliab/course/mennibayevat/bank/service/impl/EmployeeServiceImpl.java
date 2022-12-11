package tech.reliab.course.mennibayevat.bank.service.impl;

import lombok.AllArgsConstructor;
import tech.reliab.course.mennibayevat.bank.entity.Bank;
import tech.reliab.course.mennibayevat.bank.entity.BankOffice;
import tech.reliab.course.mennibayevat.bank.entity.Employee;
import tech.reliab.course.mennibayevat.bank.repository.EmployeeRepository;
import tech.reliab.course.mennibayevat.bank.service.BankService;
import tech.reliab.course.mennibayevat.bank.service.EmployeeService;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private BankService bankService;

    private static Long id = 0L;

    /**
     * Создает сотрудника банка, увеличивает число сотрудников в банке
     */
    @Override
    public Employee create(String fullName, String post, Bank bank, BankOffice office) {
        Random random = new Random();
        var employee = new Employee()
                .setId(id++)
                .setFullName(fullName)
                .setBirthday(Date.from(Instant.now()))
                .setPost(post)
                .setBank(bank)
                .setIsRemotely(random.nextBoolean())
                .setOffice(office)
                .setIsMakeLoan(random.nextBoolean())
                .setSalary(random.nextInt(100_000));
        employeeRepository.save(employee);
        bankService.addEmployee(bank);

        return employee;
    }

    @Override
    public Employee getEmployee() {

        return employeeRepository.getEntity();
    }

    @Override
    public void update(Employee employee) {

        employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {

        employeeRepository.delete(employee);
        bankService.deleteEmployee(employee.getBank());
    }
}
