package tech.reliab.course.mennibayevat.bank;

import tech.reliab.course.mennibayevat.bank.repository.*;
import tech.reliab.course.mennibayevat.bank.service.*;
import tech.reliab.course.mennibayevat.bank.service.impl.*;

public class Main {
    public static void main(String[] args) {

        BankRepository bankRepository = new BankRepository();
        BankAtmRepository bankAtmRepository = new BankAtmRepository();
        BankOfficeRepository bankOfficeRepository = new BankOfficeRepository();
        CreditAccountRepository creditAccountRepository = new CreditAccountRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        PaymentAccountRepository paymentAccountRepository = new PaymentAccountRepository();
        UserRepository userRepository = new UserRepository();

        BankService bankService = new BankServiceImpl(bankRepository);
        BankAtmService atmService = new BankAtmServiceImpl(bankAtmRepository, bankService);
        BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankOfficeRepository, bankService);
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository, bankService);
        CreditAccountService creditAccountService = new CreditAccountServiceImpl(creditAccountRepository, bankRepository);
        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(paymentAccountRepository);
        UserService userService = new UserServiceImpl(userRepository, employeeRepository, bankService, paymentAccountService, creditAccountService);


        var bank = bankService.createBank("Банк 1");

        var bankOffice = bankOfficeService.create("Офис 1", "Адрес 1, улица 1, дом 1", bank);

        var employee = employeeService.create("Рабов Работник Работович", "Старший юрист", bank, bankOffice);

        var atm = atmService.create("Банкомат 1", bank, bankOffice, employee);

        var user = userService.create("Клиентов Клиент Клиентович", "Дикси", bank);
        var creditAccount = creditAccountRepository.getEntity();
        var paymentAccount = paymentAccountRepository.getEntity();

        System.out.println(bank);
        System.out.println(bankOffice);
        System.out.println(employee);
        System.out.println(atm);
        System.out.println(user);
        System.out.println(creditAccount);
        System.out.println(paymentAccount);
    }
}
