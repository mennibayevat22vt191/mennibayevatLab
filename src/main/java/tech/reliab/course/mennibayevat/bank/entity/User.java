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
public class User {
    private Long id;
    private String fullName;
    private Date birthday;
    private String workPlace;
    private Integer monthlyIncome;
    @ToString.Exclude
    private List<Bank> banks;
    @ToString.Exclude
    private List<CreditAccount> creditAccounts;
    @ToString.Exclude
    private List<PaymentAccount> paymentAccounts;
    private Integer rate;

    public String creditAccountsInfo() {
        StringBuilder info = new StringBuilder();
        for (CreditAccount account : creditAccounts) {
            info.append(String.format("id = '%s'; Банк '%s'; Срок кредита '%s'; Сумма кредита = '%s'; Месячные выплаты = '%s'\n",
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
            info.append(String.format("id = '%s'; Банк '%s'; Остаток на счёте = '%s'\n",
                    account.getId(),
                    account.getBank(),
                    account.getMoneyAmount()
            ));
        }
        return info.toString();
    }

    @Override
    public String toString() {
        return "Данные пользователя:\n" +
                "id =" + id +
                ", ФИО ='" + fullName + '\'' +
                ", заработок =" + monthlyIncome +
                ", рейтинг =" + rate +
                '\n';
    }
}
