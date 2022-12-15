package tech.reliab.course.mennibayevat.bank.repository;

import lombok.Getter;
import tech.reliab.course.mennibayevat.bank.entity.Bank;

import java.util.ArrayList;
import java.util.Objects;

@Getter
public class BankRepository implements Repository<Bank> {

    private final ArrayList<Bank> banks = new ArrayList<>();

    public void addEntity(Bank entity) {
        this.banks.add(entity);
    }

    public void delete(Bank entity) {
        for (Bank bank : this.banks) {
            if (Objects.equals(bank, entity)) {
                this.banks.set(Math.toIntExact(bank.getId()), null);
                break;
            }
        }
    }

    public void save(Bank entity) {
        for (Bank bank : this.banks) {
            if (Objects.equals(bank, entity)) {
                this.banks.set(Math.toIntExact(bank.getId()), entity);
                break;
            }
        }
    }

    public Bank getByName(String name) {
        for (Bank bank : this.banks) {
            if (bank.getName().equals(name))
                return bank;
        }
        return null;
    }
}
