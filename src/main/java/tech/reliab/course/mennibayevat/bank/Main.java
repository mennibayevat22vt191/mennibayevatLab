package tech.reliab.course.mennibayevat.bank;

import tech.reliab.course.mennibayevat.bank.entity.User;
import tech.reliab.course.mennibayevat.bank.repository.*;
import tech.reliab.course.mennibayevat.bank.service.*;
import tech.reliab.course.mennibayevat.bank.service.impl.*;

import java.time.LocalDate;

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
        BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankOfficeRepository, employeeRepository, bankService);
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository, bankService);
        CreditAccountService creditAccountService = new CreditAccountServiceImpl(creditAccountRepository, bankRepository);
        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(paymentAccountRepository);
        UserService userService = new UserServiceImpl(userRepository, bankService);

        var officeNumber = 1;
        var atmNumber = 1;
        for (long i = 0L; i < 5; i++) {

            bankService.create("Банк " + i);

            for (long k = 0L; k < 3; k++) {

                var office = bankOfficeService.create("Офис " + officeNumber, "Адрес 1, улица 1, дом 1", bankService.getByName("Банк " + i));

                for (long j = 0L; j < 5; j++) {
                    employeeService.create("Рабов Работник Работович", "Старший юрист", bankService.getByName("Банк " + i), bankOfficeService.getBankOfficeByName("Офис " + i));

                    User user = userService.create("К.К. Клиентов", "Дикси", bankService.getByName("Банк " + i));

                    paymentAccountService.create(user, "Банк " + i);
                    paymentAccountService.create(user, "Банк " + i);

                    creditAccountService.create(user, "Банк " + i,
                            LocalDate.now(), LocalDate.now(),
                            100_000L, 1000L,
                            employeeService.getRandomEmployeeByBank(bankService.getByName("Банк " + i)), userService.getRandomPaymentAccount(user));
                    creditAccountService.create(user, "Банк " + i,
                            LocalDate.now(), LocalDate.now(),
                            100_000L, 1000L,
                            employeeService.getRandomEmployeeByBank(bankService.getByName("Банк " + i)), userService.getRandomPaymentAccount(user));
                }

                bankAtmService.create("Банкомат " + atmNumber, bankService.getByName("Банк " + i), bankOfficeService.getBankOfficeByName("Офис " + officeNumber), bankOfficeService.getRandomEmployer(office));

                atmNumber++;
                officeNumber++;
            }
        }

        System.out.println(bankService.bankInfo("Банк 0"));
        System.out.println(userService.userAccountsInfo(userService.getUserById(0L)));
    }
}
