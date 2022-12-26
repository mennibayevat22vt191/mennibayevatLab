package tech.reliab.course.mennibayevat.bank;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import tech.reliab.course.mennibayevat.bank.entity.Bank;
import tech.reliab.course.mennibayevat.bank.entity.User;
import tech.reliab.course.mennibayevat.bank.repository.*;
import tech.reliab.course.mennibayevat.bank.service.*;
import tech.reliab.course.mennibayevat.bank.service.impl.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    static BankRepository bankRepository = new BankRepository();
    static BankAtmRepository bankAtmRepository = new BankAtmRepository();
    static BankOfficeRepository bankOfficeRepository = new BankOfficeRepository();
    static CreditAccountRepository creditAccountRepository = new CreditAccountRepository();
    static EmployeeRepository employeeRepository = new EmployeeRepository();
    static PaymentAccountRepository paymentAccountRepository = new PaymentAccountRepository();
    static UserRepository userRepository = new UserRepository();

    static BankService bankService = new BankServiceImpl(bankRepository, bankOfficeRepository, bankAtmRepository,
            employeeRepository, creditAccountRepository, paymentAccountRepository);
    static BankAtmService bankAtmService = new BankAtmServiceImpl(bankAtmRepository, bankService);
    static BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankOfficeRepository, employeeRepository,
            bankService);
    static EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository, bankService);
    static PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(paymentAccountRepository,
            bankService);
    static UserService userService = new UserServiceImpl(userRepository, bankService);
    static CreditAccountService creditAccountService = new CreditAccountServiceImpl(creditAccountRepository,
            bankRepository);

    public static ObjectMapper objectMapper = new ObjectMapper();

    static {objectMapper.registerModule(new JavaTimeModule());}

    public static void main(String[] args) throws IOException {

        var officeNumber = 1;
        var atmNumber = 1;
        for (long i = 0L; i < 5; i++) {

            bankService.create("Банк " + i);

            for (long k = 0L; k < 9; k++) {

                var office = bankOfficeService.create("Офис " + officeNumber, "Адрес 1, улица 1, дом 1",
                        bankService.getByName("Банк " + i));

                for (long j = 0L; j < 5; j++) {
                    employeeService.create("Рабов Работник Работович", "Старший юрист",
                            bankService.getByName("Банк " + i),
                            bankOfficeService.getBankOfficeByName("Офис " + officeNumber));

                    var user = userService.create("К.К. Клиентов", "Дикси",
                            bankService.getByName("Банк " + i));

                    paymentAccountService.create(user, "Банк " + i);
                    paymentAccountService.create(user, "Банк " + i);

                    creditAccountService.create(user, "Банк " + i,
                            LocalDate.now(), LocalDate.now(),
                            100_000L, 1000L,
                            employeeService.getRandomEmployeeByBank(bankService.getByName("Банк " + i)),
                            userService.getRandomPaymentAccount(user));
                    creditAccountService.create(user, "Банк " + i,
                            LocalDate.now(), LocalDate.now(),
                            100_000L, 1000L,
                            employeeService.getRandomEmployeeByBank(bankService.getByName("Банк " + i)),
                            userService.getRandomPaymentAccount(user));
                }

                bankAtmService.create("Банкомат " + atmNumber, bankService.getByName("Банк " + i),
                        bankOfficeService.getBankOfficeByName("Офис " + officeNumber),
                        bankOfficeService.getRandomEmployer(office));

                atmNumber++;
                officeNumber++;
            }
        }

        var user = userService.getUserById(0L);
        paymentAccountService.create(user, "Банк " + 2);
        paymentAccountService.create(user, "Банк " + 2);
        paymentAccountService.create(user, "Банк " + 3);
        paymentAccountService.create(user, "Банк " + 3);
        creditAccountService.create(user, "Банк " + 2,
                LocalDate.now(), LocalDate.now().plusMonths(5),
                100_000L, 1000L,
                employeeService.getRandomEmployeeByBank(bankService.getByName("Банк " + 2)),
                userService.getRandomPaymentAccount(user, "Банк " + 2));
        creditAccountService.create(user, "Банк " + 2,
                LocalDate.now(), LocalDate.now().plusMonths(2),
                100_000L, 1000L,
                employeeService.getRandomEmployeeByBank(bankService.getByName("Банк " + 2)),
                userService.getRandomPaymentAccount(user, "Банк " + 2));
        creditAccountService.create(user, "Банк " + 3,
                LocalDate.now(), LocalDate.now().plusMonths(8),
                100_000L, 1000L,
                employeeService.getRandomEmployeeByBank(bankService.getByName("Банк " + 3)),
                userService.getRandomPaymentAccount(user, "Банк " + 3));
        creditAccountService.create(user, "Банк " + 3,
                LocalDate.now(), LocalDate.now().plusMonths(15),
                100_000L, 1000L,
                employeeService.getRandomEmployeeByBank(bankService.getByName("Банк " + 3)),
                userService.getRandomPaymentAccount(user, "Банк " + 3));
        creditAccountService.create(user, "Банк " + 3,
                LocalDate.now(), LocalDate.now().plusMonths(7),
                100_000L, 1000L,
                employeeService.getRandomEmployeeByBank(bankService.getByName("Банк " + 3)),
                userService.getRandomPaymentAccount(user, "Банк " + 3));
        transferAccounts(user);
    }

    private static void transferAccounts(User user) throws IOException {
        System.out.println(user + "Платёжные аккаунты: \n" + user.paymentAccountsInfo() + "Кредитный аккаунты: \n" + user.creditAccountsInfo() + '\n');
        System.out.print("Введите id банка из которого вы хотите перевести счета: ");
        var pastBankId = scanner.nextLong();
        var pastBank = bankRepository.getById(pastBankId);
        System.out.print("Введите id банка в который вы хотите перевести счета: ");
        var newBankId = scanner.nextLong();
        var newBank = bankRepository.getById(newBankId);

        if (transferAccountsToAnotherBank(user, pastBank, newBank)) {
            System.out.println(user + "Платёжные аккаунты: \n" + user.paymentAccountsInfo() + "Кредитный аккаунты: \n" + user.creditAccountsInfo() + '\n');
        } else {
            System.out.println("Что-то пошло не так");
        }
    }

    private static boolean transferAccountsToAnotherBank(User user, Bank oldBank, Bank newBank) throws IOException {
        var usersPaymentsAccounts = user.getPaymentAccounts().stream()
                .filter(paymentAccount -> paymentAccount.getBank().equals(oldBank.getName()))
                .toList();
        var usersCreditAccounts = user.getCreditAccounts().stream()
                .filter(creditAccount -> creditAccount.getBankName().equals(oldBank.getName()))
                .toList();

        var paymentAccountsJsonFileName = "paymentAccount.json";
        var creditAccountsJsonFileName = "creditAccount.json";

        FileWriter paymentAccountsJsonFile = new FileWriter(paymentAccountsJsonFileName);
        paymentAccountsJsonFile.write(objectMapper.writeValueAsString(usersPaymentsAccounts));
        paymentAccountsJsonFile.close();

        FileWriter creditAccountsJsonFile = new FileWriter(creditAccountsJsonFileName);
        creditAccountsJsonFile.write(objectMapper.writeValueAsString(usersCreditAccounts));
        creditAccountsJsonFile.close();

        try {
            creditAccountRepository.delete(usersCreditAccounts);
            paymentAccountRepository.delete(usersPaymentsAccounts);

            bankService.migrateUsersPaymentAccountsFromFile(user, newBank, paymentAccountsJsonFileName);
            bankService.migrateUsersCreditAccountsFromFile(user, newBank, creditAccountsJsonFileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}