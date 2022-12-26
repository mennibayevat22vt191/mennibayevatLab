package tech.reliab.course.mennibayevat.bank.repository;

import lombok.Getter;
import tech.reliab.course.mennibayevat.bank.entity.BankAtm;
import tech.reliab.course.mennibayevat.bank.entity.BankOffice;
import tech.reliab.course.mennibayevat.bank.utils.enums.BankAtmStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class BankAtmRepository implements Repository<BankAtm> {

    private final ArrayList<BankAtm> atms = new ArrayList<>();

    public void addEntity(BankAtm entity) {
        this.atms.add(entity);
    }

    public void delete(BankAtm entity) {
        for (BankAtm bankAtm : this.atms) {
            if (Objects.equals(bankAtm, entity)) {
                this.atms.set(Math.toIntExact(bankAtm.getId()), null);
                break;
            }
        }
    }

    @Override
    public void save(BankAtm entity) {
        for (BankAtm bankAtm : this.atms) {
            if (Objects.equals(bankAtm, entity)) {
                this.atms.set(Math.toIntExact(bankAtm.getId()), entity);
                break;
            }
        }
    }

    public BankAtm getById(Long id) {
        for (BankAtm atm : this.atms) {
            if (atm.getId().equals(id))
                return atm;
        }
        return null;
    }

    public List<BankAtm> getAllByOfficeLocationAndWorksAndMoneyContains(BankOffice bankOffice, Long moneyAmount) {
        return atms.stream()
                .filter(bankAtm -> bankAtm.getLocation().equals(bankOffice.getAddress()) &&
                        bankAtm.getStatus().equals(BankAtmStatus.WORKING) &&
                        bankAtm.getMoneyStock() > moneyAmount &&
                        bankAtm.getBankOwner().equals(bankOffice.getBank())
                )
                .collect(Collectors.toList());
    }
}
