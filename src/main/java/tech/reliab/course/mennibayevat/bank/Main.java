package tech.reliab.course.mennibayevat.bank;

import tech.reliab.course.mennibayevat.bank.entity.BankOffice;
import tech.reliab.course.mennibayevat.bank.entity.User;
import tech.reliab.course.mennibayevat.bank.exception.BankException;
import tech.reliab.course.mennibayevat.bank.exception.BankOfficeException;
import tech.reliab.course.mennibayevat.bank.exception.CreditAmountException;
import tech.reliab.course.mennibayevat.bank.exception.EmployeeException;
import tech.reliab.course.mennibayevat.bank.repository.*;
import tech.reliab.course.mennibayevat.bank.service.*;
import tech.reliab.course.mennibayevat.bank.service.impl.*;

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

    static BankService bankService = new BankServiceImpl(bankRepository, bankOfficeRepository, bankAtmRepository);
    static BankAtmService bankAtmService = new BankAtmServiceImpl(bankAtmRepository, bankService);
    static BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankOfficeRepository, employeeRepository, bankService);
    static EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository, bankService);
    static PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(paymentAccountRepository, bankService);
    static UserService userService = new UserServiceImpl(userRepository, bankService);
    static CreditAccountService creditAccountService = new CreditAccountServiceImpl(creditAccountRepository, bankRepository);

    public static void main(String[] args) {

        var officeNumber = 1;
        var atmNumber = 1;
        for (long i = 0L; i < 5; i++) {

            bankService.create("Банк " + i);

            for (long k = 0L; k < 9; k++) {

                var office = bankOfficeService.create("Офис " + officeNumber, "Адрес 1, улица 1, дом 1", bankService.getByName("Банк " + i));

                for (long j = 0L; j < 5; j++) {
                    employeeService.create("Рабов Работник Работович", "Старший юрист", bankService.getByName("Банк " + i), bankOfficeService.getBankOfficeByName("Офис " + officeNumber));

                    var user = userService.create("К.К. Клиентов", "Дикси", bankService.getByName("Банк " + i));
                    paymentAccountService.create(user, "Банк " + i);
                    userService.create("К.К. Клиентов", "Дикси", bankService.getByName("Банк " + i));
                }

                bankAtmService.create("Банкомат " + atmNumber, bankService.getByName("Банк " + i), bankOfficeService.getBankOfficeByName("Офис " + officeNumber), bankOfficeService.getRandomEmployer(office));

                atmNumber++;
                officeNumber++;
            }
        }

        try {
            System.out.print("Введите ваш id: ");
            var userId = scanner.nextLong();
            var user = userRepository.getById(userId);
            if (user == null) {
                System.out.println("Пользователя с таким id не существует");
            } else {
                System.out.print("Введите сумму кредита: ");
                var creditAmount = scanner.nextLong();
                getCredit(user, creditAmount);
            }
        } catch (Exception e) {
            System.out.println("Что-то пошло не так! Причина: " + e.getMessage());
        }
    }

    private static void getCredit(User user, Long creditAmount) throws Exception {
        if (creditAmount < 10000) {
            throw new CreditAmountException("Сумма кредита < 10000р");
        } else if (creditAmount > 1_000_000_00) {
            throw new CreditAmountException("Сумма кредита > 100 000 000р");
        } else {
            var bank = bankService.getBestBank();
            System.out.println("Лучший банк для вас: " + bank.getName());

            var offices = bankOfficeService.getAllWorksOffices(bank);
            if (offices == null || offices.isEmpty()) {
                throw new BankException("В банке нет работающих офисов, выдающих кредит");
            }

            if (offices.stream().filter(office -> office.getMoneyStock() > creditAmount).findFirst().isEmpty()) {
                throw new CreditAmountException("Ни в одном офисе банка нет нужной суммы");
            }

            System.out.println("В этих офисах можно взять кредит: ");
            var availableOffices = offices.stream().filter(office -> office.getMoneyStock() > creditAmount).toList();

            availableOffices.forEach(office -> {
                System.out.printf("Название '%s'; Адрес '%s'; id '%d'%n", office.getName(), office.getAddress(), office.getId());
            });
            System.out.print("Выберите id оффиса: ");

            BankOffice office;
            do {
                var officeId = scanner.nextLong();
                office = offices.stream().filter(el -> el.getId().equals(officeId)).findFirst().orElse(null);

                if (office == null) {
                    System.out.println("Неверный id");
                }
            } while (office == null);

            var employees = employeeRepository.findAllCreditAvailableByOffice(office);
            if (employees.isEmpty()) {
                throw new EmployeeException("Нет сотрудников, которые могут выдать кредит");
            }

            var employee = employees.get(random.nextInt(employees.size()));
            System.out.println("C вами будет работать " + employee.getFullName());

            var paymentAccount = paymentAccountRepository.getPaymentAccountByBankAndUser(bank, user);
            if (paymentAccount != null) {
                System.out.println("Вы уже наш клиент");
            } else {
                System.out.println("Вы ещё не наш клиент. Создаём аккаунт");
                paymentAccount = paymentAccountService.create(user, bank.getName());
            }

            System.out.println("Ваш кредитный рейтинг = " + user.getRate());
            System.out.println("Рейтинг банка = " + bank.getRate());

            if (user.getRate() < 500 && bank.getRate() > 50) {
                throw new BankException("Вам отказано в выдаче кредита");
            }
            System.out.println("Вам одобрен кредит. Создаем кредитный аккаунт.");

            long loanLength;
            do {
                System.out.print("На какой срок вы планируете взять кредит(от 12 до 24 месяцев): ");
                loanLength = scanner.nextLong();
            } while (loanLength > 24 || loanLength < 12);

            var creditBegin = LocalDate.now();
            var creditEnd = LocalDate.now().plusMonths(loanLength);
            var monthlyPayment = (creditAmount * (1 + bank.getInterestRate() / 100)) / loanLength;

            creditAccountService.create(user, bank.getName(),
                    creditBegin, creditEnd,
                    creditAmount,
                    monthlyPayment,
                    employee, paymentAccount);

            System.out.println("Кредит успешно оформлен");

            var atms = bankAtmRepository.getAllByOfficeLocationAndWorksAndMoneyContains(office, creditAmount);

            if (!atms.isEmpty()) {
                System.out.println("В нашем банкомате недостаточно средств");
                atms = bankService.getAtmsToExtraditeCredit(bank, creditAmount);
                System.out.println("Адреса банкоматов, с достаточным количеством средств: ");
                atms.forEach(bankAtm -> System.out.println(bankAtm.getLocation()));
            } else {
                throw new BankOfficeException("Нет банков с достаточным количеством средств");
            }
        }
    }
}
