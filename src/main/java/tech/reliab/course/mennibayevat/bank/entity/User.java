package tech.reliab.course.mennibayevat.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class User {
    private Long id;
    private String fullName;
    private Date birthday;
    private String workPlace;
    private Integer monthlyIncome;
    private List<Bank> banks;
    private List<CreditAccount> creditAccounts;
    private List<PaymentAccount> paymentAccounts;
    private Integer rate;

    public String creditAccountsInfo() {
        StringBuilder info = new StringBuilder();
        for (CreditAccount account : creditAccounts) {
            info.append(String.format("id=%s\nБанк=%s\nСрок кредита=%s\nСумма кредита=%s\nМесячные выплаты=%s\n",
                    account.getId(),
                    account.getBankName(),
                    account.getLoanPeriod(),
                    account.getLoanAmount(),
                    account.getMonthlyPayment()
            ));
        }
        return info.toString();
    }

    public String paymentAccountsInfo() {
        StringBuilder info = new StringBuilder();
        for (PaymentAccount account : paymentAccounts) {
            info.append(String.format("id=%s\nБанк=%s\nОстаток на счёте=%s\n",
                    account.getId(),
                    account.getBank(),
                    account.getMoneyAmount()
            ));
        }
        return info.toString();
    }
}
