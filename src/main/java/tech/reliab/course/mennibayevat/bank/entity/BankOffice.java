package tech.reliab.course.mennibayevat.bank.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import tech.reliab.course.mennibayevat.bank.utils.enums.BankOfficeStatus;

import java.util.List;

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
    @JsonIgnore
    private List<BankAtm> atms;
    private Boolean creditAvailable;
    private Boolean isWithdraw;
    private Boolean isDeposit;
    private Long moneyStock;
    private Integer rental;
    private Bank bank;
}
