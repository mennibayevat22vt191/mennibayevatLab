package tech.reliab.course.mennibayevat.bank.repository;

import lombok.Getter;
import tech.reliab.course.mennibayevat.bank.entity.CreditAccount;
import tech.reliab.course.mennibayevat.bank.entity.PaymentAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class CreditAccountRepository implements Repository<CreditAccount> {

    private final List<CreditAccount> entities = new ArrayList<>();

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

    public void save(List<CreditAccount> accounts) {
        this.entities.addAll(accounts);
    }

    public void delete(List<CreditAccount> accounts) {
        entities.removeAll(accounts);
    }
}
