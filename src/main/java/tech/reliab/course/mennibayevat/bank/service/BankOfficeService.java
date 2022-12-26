package tech.reliab.course.mennibayevat.bank.service;

import tech.reliab.course.mennibayevat.bank.entity.Bank;
import tech.reliab.course.mennibayevat.bank.entity.BankOffice;
import tech.reliab.course.mennibayevat.bank.entity.Employee;

import java.util.List;

public interface BankOfficeService {
    BankOffice create(String name, String address, Bank bank);

    BankOffice getBankOfficeByName(String name);

    Employee getRandomEmployer(BankOffice office);

    List<BankOffice> getAllWorksOffices(Bank bank);

    void update(BankOffice bankOffice);

    void delete(BankOffice bankOffice);
}
