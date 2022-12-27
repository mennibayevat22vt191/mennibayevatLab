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
    private static Long id = 0L;
    private EmployeeRepository employeeRepository;
    private BankService bankService;

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
        employeeRepository.addEntity(employee);
        bankService.addEmployee(bank, employee);

        return employee;
    }

    @Override
    public Employee getEmployeeById(Long id) {

        return employeeRepository.getById(id);
    }

    @Override
    public Employee getRandomEmployeeByBank(Bank bank) {
        return employeeRepository.getEntities()
                .stream()
                .filter(el -> el.getBank() == bank)
                .findAny()
                .orElseThrow();
    }

    @Override
    public void update(Employee employee) {

        employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {

        employeeRepository.delete(employee);
        bankService.deleteEmployee(employee.getBank(), employee);
    }
}
