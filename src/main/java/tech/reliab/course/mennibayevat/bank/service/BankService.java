package tech.reliab.course.mennibayevat.bank.service;

import tech.reliab.course.mennibayevat.bank.entity.Bank;
import tech.reliab.course.mennibayevat.bank.entity.BankAtm;

import java.util.ArrayList;
import java.util.List;

public interface BankService {
    Bank create(String name);

    ArrayList<Bank> getBanks();

    Bank getByName(String name);

    void update(Bank bank);

    void delete(Bank bank);

    String bankInfo(String name);

    Bank getBestBank();

    List<BankAtm> getAtmsToExtraditeCredit(Bank bank, Long creditAmount);

    void addAtm(Bank bank);

    void deleteAtm(Bank bank);

    void addBankOffice(Bank bank);

    void deleteBankOffice(Bank bank);

    void addEmployee(Bank bank);

    void deleteEmployee(Bank bank);

    void addClient(Bank bank);

    void deleteClient(Bank bank);
}
