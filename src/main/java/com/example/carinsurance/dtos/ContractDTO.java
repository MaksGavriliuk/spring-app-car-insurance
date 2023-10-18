package com.example.carinsurance.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class ContractDTO {
    private int id;
    private Date startDate;
    private Date endDate;
    private BigDecimal amount;
    private BigDecimal payoutAmount;
    private String status;
    private int userCarId;
    private int insuranceAgentId;
    private int insuranceTypeId;
}
