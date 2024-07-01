package com.tarifftales.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantMAWBRPDTO {

    private String rateType;
    private String carrierName;
    private Date validFrom;
    private Date validTo;
    private Boolean active;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;

}
