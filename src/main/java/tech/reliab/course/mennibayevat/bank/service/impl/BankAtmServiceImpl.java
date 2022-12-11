package tech.reliab.course.mennibayevat.bank.service.impl;

import lombok.AllArgsConstructor;
import tech.reliab.course.mennibayevat.bank.entity.Bank;
import tech.reliab.course.mennibayevat.bank.entity.BankAtm;
import tech.reliab.course.mennibayevat.bank.entity.BankOffice;
import tech.reliab.course.mennibayevat.bank.entity.Employee;
import tech.reliab.course.mennibayevat.bank.repository.BankAtmRepository;
import tech.reliab.course.mennibayevat.bank.service.BankAtmService;
import tech.reliab.course.mennibayevat.bank.service.BankService;
import tech.reliab.course.mennibayevat.bank.utils.enums.BankAtmStatus;

import java.util.Random;

@AllArgsConstructor
public class BankAtmServiceImpl implements BankAtmService {
    private BankAtmRepository bankAtmRepository;
    private BankService bankService;
    private static Long id = 0L;

    @Override
    public BankAtm create(String name, Bank bank, BankOffice office, Employee employee) {
        Random random = new Random();
        var bankAtm = new BankAtm()
                .setId(id++)
                .setName(name)
                .setLocation(office.getAddress())
                .setStatus(BankAtmStatus.getRandomStatus())
                .setBankOwner(bank)
                .setServingEmployee(employee)
                .setIsWithdraw(random.nextBoolean())
                .setIsDeposit(random.nextBoolean())
                .setMoneyStock(bank.getMoneyStock())
                .setMaintenancePrice(random.nextInt(10000));

        bankAtmRepository.save(bankAtm);
        bankService.addAtm(bank);

        return bankAtm;
    }

    @Override
    public BankAtm getBankAtm() {

        return bankAtmRepository.getEntity();
    }

    @Override
    public void delete(BankAtm bankAtm) {

        bankAtmRepository.delete(bankAtm);
        bankService.deleteAtm(bankAtm.getBankOwner());

    }

    @Override
    public void update(BankAtm bankAtm) {

        bankAtmRepository.save(bankAtm);
    }

}
