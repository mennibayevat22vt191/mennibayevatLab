package tech.reliab.course.mennibayevat.bank.repository;

import lombok.Getter;
import tech.reliab.course.mennibayevat.bank.entity.BankOffice;

import java.util.ArrayList;
import java.util.Objects;

@Getter
public class BankOfficeRepository implements Repository<BankOffice> {

    private final ArrayList<BankOffice> entities = new ArrayList<>();

    public void addEntity(BankOffice entity) {
        this.entities.add(entity);
    }

    public void delete(BankOffice entity) {
        for (BankOffice office : this.entities)
            if (Objects.equals(office, entity)) {
                this.entities.set(Math.toIntExact(office.getId()), null);
                break;
            }
    }

    public void save(BankOffice entity) {
        for (BankOffice office : this.entities)
            if (Objects.equals(office, entity)) {
                this.entities.set(Math.toIntExact(office.getId()), entity);
                break;
            }
    }

    public BankOffice getByName(String name) {
        for (BankOffice bankOffice : this.entities) {
            if (bankOffice.getName().equals(name))
                return bankOffice;
        }
        return null;
    }
}
