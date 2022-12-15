package tech.reliab.course.mennibayevat.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import tech.reliab.course.mennibayevat.bank.utils.enums.BankAtmStatus;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
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
