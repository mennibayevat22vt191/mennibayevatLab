package tech.reliab.course.mennibayevat.bank.repository;

import lombok.Getter;
import tech.reliab.course.mennibayevat.bank.entity.CreditAccount;
import tech.reliab.course.mennibayevat.bank.entity.PaymentAccount;

import java.util.ArrayList;
import java.util.Objects;

@Getter
public class PaymentAccountRepository implements Repository<PaymentAccount> {

    private final ArrayList<PaymentAccount> entities = new ArrayList<>();

    public void addEntity(PaymentAccount entity) {
        this.entities.add(entity);
    }

    public void delete(PaymentAccount entity) {
        for (PaymentAccount account : this.entities)
            if (Objects.equals(account, entity)) {
                this.entities.set(Math.toIntExact(account.getId()), null);
                break;
            }
    }

    public void save(PaymentAccount entity) {
        for (PaymentAccount account : this.entities)
            if (Objects.equals(account, entity)) {
                this.entities.set(Math.toIntExact(account.getId()), entity);
                break;
            }
    }
}
