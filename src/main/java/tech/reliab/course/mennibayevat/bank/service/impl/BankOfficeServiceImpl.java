package tech.reliab.course.mennibayevat.bank.service.impl;

import lombok.AllArgsConstructor;
import tech.reliab.course.mennibayevat.bank.entity.Bank;
import tech.reliab.course.mennibayevat.bank.entity.BankOffice;
import tech.reliab.course.mennibayevat.bank.repository.BankOfficeRepository;
import tech.reliab.course.mennibayevat.bank.service.BankOfficeService;
import tech.reliab.course.mennibayevat.bank.service.BankService;
import tech.reliab.course.mennibayevat.bank.utils.enums.BankOfficeStatus;

import java.util.Random;

@AllArgsConstructor
public class BankOfficeServiceImpl implements BankOfficeService {
    private BankOfficeRepository bankOfficeRepository;
    private BankService bankService;
    private static Long id = 0L;

    /**
     * Создает офис, увеличивает количество офисов в банке
     */
    @Override
    public BankOffice create(String name, String address, Bank bank) {
        Random random = new Random();

        var bankOffice = new BankOffice()
                .setId(id++)
                .setName(name)
                .setAddress(address)
                .setStatus(BankOfficeStatus.getRandomStatus())
                .setAccommodateAtmAvailable(random.nextBoolean())
                .setAtmCount(bank.getAtmCount())
                .setCreditAvailable(random.nextBoolean())
                .setIsWithdraw(random.nextBoolean())
                .setIsDeposit(random.nextBoolean())
                .setMoneyStock(bank.getMoneyStock())
                .setRental(random.nextInt(100_000))
                .setBank(bank);
        bankOfficeRepository.save(bankOffice);
        bankService.addBankOffice(bank);

        return bankOffice;
    }

    @Override
    public BankOffice getBankOffice() {

        return bankOfficeRepository.getEntity();
    }

    @Override
    public void update(BankOffice bankOffice) {

        bankOfficeRepository.save(bankOffice);
    }

    @Override
    public void delete(BankOffice bankOffice) {

        bankOfficeRepository.save(bankOffice);
        bankService.deleteBankOffice(bankOffice.getBank());
    }
}
