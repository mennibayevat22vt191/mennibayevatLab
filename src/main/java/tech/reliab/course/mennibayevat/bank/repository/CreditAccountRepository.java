package tech.reliab.course.mennibayevat.bank.repository;

import lombok.Getter;
import tech.reliab.course.mennibayevat.bank.entity.CreditAccount;

import java.util.ArrayList;
import java.util.Objects;

@Getter
public class CreditAccountRepository implements Repository<CreditAccount> {

    private final ArrayList<CreditAccount> entities = new ArrayList<>();

    public void addEntity(CreditAccount entity) {
        this.entities.add(entity);
    }

    public void delete(CreditAccount entity) {
        for (CreditAccount account : this.entities)
            if (Objects.equals(account, entity)) {
                this.entities.set(Math.toIntExact(account.getId()), null);
                break;
            }
    }

    public void save(CreditAccount entity) {
        for (CreditAccount account : this.entities)
            if (Objects.equals(account, entity)) {
                this.entities.set(Math.toIntExact(account.getId()), entity);
                break;
            }
    }
}
