package tech.reliab.course.mennibayevat.bank.entity;

import tech.reliab.course.mennibayevat.bank.utils.enums.BankAtmStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class BankAtm {
    private Long id;
    private String name;
    private BankAtmStatus status;
    private Bank bankOwner;
    private String location;
    private Employee servingEmployee;
    private Boolean isWithdraw;
    private Boolean isDeposit;
    private Long moneyStock;
    private Integer maintenancePrice;
}
