package tech.reliab.course.mennibayevat.bank.repository;

import lombok.Getter;
import tech.reliab.course.mennibayevat.bank.entity.BankOffice;

import java.util.Objects;

@Getter
public class BankOfficeRepository implements Repository<BankOffice> {

    private BankOffice entity;

    public void save(BankOffice entity) {
        this.entity = entity;
    }

    public void delete(BankOffice entity) {
        if (Objects.equals(this.entity, entity)) {
            this.entity = null;
        }
    }
}
