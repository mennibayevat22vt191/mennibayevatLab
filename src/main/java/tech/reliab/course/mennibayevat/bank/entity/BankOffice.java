package tech.reliab.course.mennibayevat.bank.entity;

import lombok.*;
import lombok.experimental.Accessors;
import tech.reliab.course.mennibayevat.bank.utils.enums.BankOfficeStatus;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class BankOffice {
    private Long id;
    private String name;
    private String address;
    private BankOfficeStatus status;
    private Boolean accommodateAtmAvailable;
    private Integer atmCount;
    private Boolean creditAvailable;
    private Boolean isWithdraw;
    private Boolean isDeposit;
    private Long moneyStock;
    private Integer rental;
    private Bank bank;
}
