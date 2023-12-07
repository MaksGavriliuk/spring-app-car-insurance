package com.example.carinsurance.filter;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class ContractFilter {
    private Integer brandId;
    private Integer engineVolumeId;
    private Integer fuelTypeId;
    private Integer userId;
    private Integer insuranceAgentId;
    private Integer insuranceTypeId;
    private Date startDate;
    private Date endDate;
    private BigDecimal amount;
    private BigDecimal payoutAmount;
    private String status;
}
