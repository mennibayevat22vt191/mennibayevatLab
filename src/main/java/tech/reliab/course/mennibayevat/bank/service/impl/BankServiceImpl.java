package tech.reliab.course.mennibayevat.bank.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import tech.reliab.course.mennibayevat.bank.entity.*;
import tech.reliab.course.mennibayevat.bank.repository.*;
import tech.reliab.course.mennibayevat.bank.service.BankService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static tech.reliab.course.mennibayevat.bank.Main.*;

@AllArgsConstructor
public class BankServiceImpl implements BankService {
    private static Long id = 0L;
    private final BankRepository bankRepository;
    private final BankOfficeRepository bankOfficeRepository;
    private final BankAtmRepository bankAtmRepository;
    private final EmployeeRepository employeeRepository;
    private final CreditAccountRepository creditAccountRepository;
    private final PaymentAccountRepository paymentAccountRepository;

    @Override
    public Bank create(String name) {
        Random random = new Random();
        var rate = random.nextInt(100);

        var bank = new Bank()
                .setId(id++)
                .setName(name)
                .setOfficeCount(0)
                .setAtmCount(0)
                .setEmployeeCount(0)
                .setClientCount(0)
                .setRate(rate)
                .setMoneyStock(random.nextLong(1_000_000L))
                .setInterestRate((int) (20 - rate / 10D));
        bankRepository.addEntity(bank);

        return bank;
    }

    @Override
    public Bank getByName(String name) {
        return bankRepository.getByName(name);
    }

    @Override
    public ArrayList<Bank> getBanks() {
        return bankRepository.getBanks();
    }

    @Override
    public void update(Bank bank) {
        bankRepository.save(bank);
    }

    @Override
    public void delete(Bank bank) {
        bankRepository.delete(bank);
    }

    @Override
    public String bankInfo(String name) {
        var bank = getByName(name);
        return "\nБанк" + name + ":\n" + "id=" + bank.getId() + "\nколичество банков=" + bank.getOfficeCount() +
                "\nколичество банкоматов=" + bank.getAtmCount() + "\nколичество работников=" + bank.getEmployeeCount() +
                "\nколичество клиентов=" + bank.getClientCount() + "\nрейтинг=" + bank.getRate() + "\nсредства=" +
                bank.getMoneyStock() + "\nПроцентная ставка=" + bank.getInterestRate() + "%\n\n";
    }

    @Override
    public Bank getBestBank() {
        var banks = bankRepository.getBanks();
        Bank bestBank = banks.get(0);
        for (Bank bank : banks) {
            if (bestBank.getAtmCount() < bank.getAtmCount() &&
                    bestBank.getEmployeeCount() < bank.getEmployeeCount()) {
                if (bestBank.getOfficeCount() < bank.getOfficeCount()) {
                    bestBank = bank;
                } else if (bestBank.getInterestRate() < bank.getInterestRate()) {
                    bestBank = bank;
                }
            }
        }
        return bestBank;
    }

    @Override
    public List<BankAtm> getAtmsToExtraditeCredit(Bank bank, Long creditAmount) {
        var offices = bankOfficeRepository.findAllByBank(bank);
        return offices
                .stream()
                .map(bankOffice ->
                        bankAtmRepository
                                .getAllByOfficeLocationAndWorksAndMoneyContains(bankOffice, creditAmount)
                )
                .findFirst()
                .orElse(null);

    }

    @Override
    public boolean migrateUsersCreditAccountsFromFile(User user, Bank bank, String filename) throws IOException {
        File file = new File(filename);
        List<CreditAccount> creditAccounts = objectMapper.readValue(file, new TypeReference<>(){});
        creditAccounts.forEach(creditAccount -> {
            creditAccount.setBankName(bank.getName());
            //creditAccount.setId(id++);
            creditAccount.setUser(user);
            creditAccount.getPaymentAccount().setBank(bank.getName());
            var newEmployeeList = employeeRepository.findAllCreditAvailableByBank(bank);
            creditAccount.setCreditor(newEmployeeList.get(0));
        });
        creditAccountRepository.save(creditAccounts);
        user.setCreditAccounts(creditAccounts);
        return true;
    }

    @Override
    public boolean migrateUsersPaymentAccountsFromFile(User user, Bank bank, String filename) throws IOException {
        File file = new File(filename);
        List<PaymentAccount> paymentAccounts = objectMapper.readValue(file, new TypeReference<>(){});

        paymentAccounts.forEach(paymentAccount -> {
            paymentAccount.setUser(user);
            //paymentAccount.setId(id++);
            paymentAccount.setBank(bank.getName());
        });
        paymentAccountRepository.save(paymentAccounts);
        user.setPaymentAccounts(paymentAccounts);
        return false;
    }

    /**
     * Related entities methods
     */
    @Override
    public void addAtm(Bank bank) {
        var bankAtmCount = bank.getAtmCount();
        bank.setAtmCount(++bankAtmCount);
        this.update(bank);
    }

    @Override
    public void deleteAtm(Bank bank) {
        var bankAtmCount = bank.getAtmCount();
        bank.setAtmCount(--bankAtmCount);
        this.update(bank);
    }

    @Override
    public void addBankOffice(Bank bank) {
        var officeCount = bank.getOfficeCount();
        bank.setOfficeCount(++officeCount);
        this.update(bank);
    }

    @Override
    public void deleteBankOffice(Bank bank) {
        var officeCount = bank.getOfficeCount();
        bank.setOfficeCount(--officeCount);
        this.update(bank);
    }

    @Override
    public void addEmployee(Bank bank) {
        var employeeCount = bank.getEmployeeCount();
        bank.setEmployeeCount(++employeeCount);
        this.update(bank);
    }

    @Override
    public void deleteEmployee(Bank bank) {
        var employeeCount = bank.getEmployeeCount();
        bank.setEmployeeCount(--employeeCount);
        this.update(bank);
    }

    @Override
    public void addClient(Bank bank) {
        var clientCount = bank.getClientCount();
        bank.setClientCount(++clientCount);
        this.update(bank);
    }

    @Override
    public void deleteClient(Bank bank) {
        var clientCount = bank.getClientCount();
        bank.setClientCount(--clientCount);
        this.update(bank);
    }

}
