package tech.reliab.course.mennibayevat.bank.repository;

import lombok.Getter;
import tech.reliab.course.mennibayevat.bank.entity.PaymentAccount;

import java.util.Objects;

@Getter
public class PaymentAccountRepository implements Repository<PaymentAccount> {

    private PaymentAccount entity;

    public void save(PaymentAccount entity) {
        this.entity = entity;
    }

    public void delete(PaymentAccount entity) {
        if (Objects.equals(this.entity, entity)) {
            this.entity = null;
        }
    }
}
