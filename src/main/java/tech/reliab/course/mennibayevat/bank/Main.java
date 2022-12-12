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
        BankAtmService bankAtmService = new BankAtmServiceImpl(bankAtmRepository, bankService);
        BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankOfficeRepository, bankService);
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository, bankService);
        CreditAccountService creditAccountService = new CreditAccountServiceImpl(creditAccountRepository, bankRepository);
        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(paymentAccountRepository);
        UserService userService = new UserServiceImpl(userRepository, employeeRepository, bankService, paymentAccountService, creditAccountService);

        for(int i = 1; i <= 5; i++ ) {
            bankService.createBank("Банк " + i);
            for(int j = 1; j <= 3; j++ ) {
                bankOfficeService.create("Офис " + j, "Адрес 1, улица 1, дом 1", bankService.getByName("Банк " + i));
                for (int k = 1; k <= 5; k++) {
                    employeeService.create("Рабов Работник Работович", "Старший юрист", bankService.getByName("Банк "+i), bankOfficeService.getBankOfficeByName("Офис "+j));
                }
                bankAtmService.create("Банкомат "+j, bankService.getByName("Банк " + i), bankOfficeService.getBankOfficeByName("Офис "+j), employeeService.getEmployeeById(Long.valueOf(j-1)));
            }
            for (int j = 1; j <= 5; j++) {
                userService.create("Клиентов Клиент Клиентович", "Дикси", bankService.getByName("Банк " + i));
                paymentAccountService.create(userService.getUserById(Long.valueOf(j-1)), "Банка "+i);
                paymentAccountService.create(userService.getUserById(Long.valueOf(j-1)), "Банка "+i);
                System.out.println(userService.getUserById(Long.valueOf(j-1)));
            }
            System.out.println(bankService.getByName("Банк "+i));
        }


//        bankOfficeService.create("Офис 1", "Адрес 1, улица 1, дом 1", bankService.getByName("Банк 1"));
//        bankOfficeService.create("Офис 2", "Адрес 1, улица 1, дом 1", bankService.getByName("Банк 1"));
//        bankOfficeService.create("Офис 3", "Адрес 1, улица 1, дом 1", bankService.getByName("Банк 1"));

//        var employee = employeeService.create("Рабов Работник Работович", "Старший юрист", bank, bankOffice);
//
//        var atm = atmService.create("Банкомат 1", bank1, bankOffice1, employee);
//
//        var user = userService.create("Клиентов Клиент Клиентович", "Дикси", bank1);
//
//        System.out.println(bankOfficeRepository.getEntities().get(0));
//        System.out.println(bankOfficeRepository.getEntities().get(1));
//        System.out.println(bankOfficeRepository.getEntities().get(2));
//        System.out.println(bank2);
//        System.out.println(bank3);
//        System.out.println(bankOffice1);
//        System.out.println(bankOffice2);
//        System.out.println(bankOffice3);


//        var paymentAccount = paymentAccountService.create(user, bank.getName());
//
//        var employee = employeeRepository.getEntities();
//        var creditAccount = creditAccountService
//                .create(user, bank.getName(),
//                        LocalDate.now(), LocalDate.now(),
//                        100_000L, 1000L,
//                        employee, paymentAccount);
    }
}
