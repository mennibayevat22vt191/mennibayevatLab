package tech.reliab.course.mennibayevat.bank.repository;

import lombok.Getter;
import tech.reliab.course.mennibayevat.bank.entity.Bank;
import tech.reliab.course.mennibayevat.bank.entity.PaymentAccount;
import tech.reliab.course.mennibayevat.bank.entity.User;

import java.util.ArrayList;
import java.util.List;
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

    public void save(List<PaymentAccount> accounts) {
        this.entities.addAll(accounts);
    }

    public PaymentAccount getPaymentAccountByBankAndUser(Bank bank, User user) {
        return entities.stream()
                .filter(paymentAccount ->
                        paymentAccount.getUser().equals(user) &&
                                paymentAccount.getBank().equals(bank.getName()))
                .findFirst()
                .orElse(null);
    }

    public void delete(List<PaymentAccount> accounts) {
        entities.removeAll(accounts);
    }
}
