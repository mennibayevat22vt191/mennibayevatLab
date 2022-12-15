package tech.reliab.course.mennibayevat.bank.repository;

import lombok.Getter;
import tech.reliab.course.mennibayevat.bank.entity.BankAtm;

import java.util.ArrayList;
import java.util.Objects;

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
}
