package tech.reliab.course.mennibayevat.bank.service;



import tech.reliab.course.mennibayevat.bank.entity.*;

import java.io.IOException;
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

    boolean migrateUsersCreditAccountsFromFile(User user, Bank bank, String filename) throws IOException;

    /**
     * Переводит все платедные счета пользователя в банк из файла filename
     */
    boolean migrateUsersPaymentAccountsFromFile(User user, Bank bank, String filename) throws IOException;

    void addAtm(Bank bank, BankAtm atm);

    void deleteAtm(Bank bank, BankAtm atm);

    void addBankOffice(Bank bank, BankOffice office);

    void deleteBankOffice(Bank bank, BankOffice office);

    void addEmployee(Bank bank, Employee employee);

    void deleteEmployee(Bank bank, Employee employee);

    void addClient(Bank bank, User client);

    void deleteClient(Bank bank, User client);
}
