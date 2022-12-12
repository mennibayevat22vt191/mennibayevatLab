package tech.reliab.course.mennibayevat.bank.service;

import tech.reliab.course.mennibayevat.bank.entity.Bank;
import tech.reliab.course.mennibayevat.bank.entity.BankOffice;

public interface BankOfficeService {
    BankOffice create(String name, String address, Bank bank);
    BankOffice getBankOfficeByName(String name);
    void update(BankOffice bankOffice);
    void delete(BankOffice bankOffice);
}
