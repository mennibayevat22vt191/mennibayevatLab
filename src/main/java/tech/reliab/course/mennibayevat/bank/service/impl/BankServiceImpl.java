package tech.reliab.course.mennibayevat.bank.service.impl;

import tech.reliab.course.mennibayevat.bank.entity.Bank;
import tech.reliab.course.mennibayevat.bank.service.BankService;

import java.util.Random;

public class BankServiceImpl implements BankService {
    private static Long id = 0L;
    //TODO #1 private BankRepository bankRepository = new BankRepository()

    @Override
    public Bank createBank(String name) {
        Random random = new Random();
        var rate = random.nextInt(100);

        var bank = new Bank()
                .setId(id++)
                .setName(name)
                .setOfficeCount(0)
                .setAtmCount(0)
                .setEmployeeCount(0)
                .setClientCount(0)
                .setRate(0)
                .setMoneyStock(random.nextLong(1_000_000L))
                .setInterestRate((int) (20 - (rate * 20) / 10D));
        //TODO #1 bankRepository.save(bank)

        return bank;
    }

    @Override
    public Bank getBank() {
        return null;
        //TODO #1 return bankRepository.getBank;
    }

    @Override
    public void update(Bank bank) {
        //TODO #1 bankRepository.save(bank)
    }

    @Override
    public void delete(Bank bank) {
        //TODO #1 bankRepository.delete(bank)
    }

    /**
     *
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
