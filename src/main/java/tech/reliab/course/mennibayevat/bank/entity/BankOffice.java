package tech.reliab.course.mennibayevat.bank.entity;

import tech.reliab.course.mennibayevat.bank.utils.enums.BankOfficeStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
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
