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
    private static Long id = 0L;
    private BankAtmRepository bankAtmRepository;
    private BankService bankService;

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

        bankAtmRepository.addEntity(bankAtm);
        bankService.addAtm(bank, bankAtm);

        return bankAtm;
    }

    @Override
    public BankAtm getAtmById(Long id) {

        return bankAtmRepository.getById(id);
    }

    @Override
    public void delete(BankAtm bankAtm) {

        bankAtmRepository.delete(bankAtm);
        bankService.deleteAtm(bankAtm.getBankOwner(), bankAtm);

    }

    @Override
    public void update(BankAtm bankAtm) {

        bankAtmRepository.save(bankAtm);
    }

}
