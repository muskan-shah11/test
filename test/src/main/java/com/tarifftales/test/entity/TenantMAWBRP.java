package com.tarifftales.test.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tenant_MAWB_RP")
public class TenantMAWBRP {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "Charge_ID", length = 36)
	private String chargeId;

    @Column(name = "Rate_Type", length = 255)
    private String rateType;

    @Column(name = "Carrier_Name", nullable = false, length = 255)
    private String carrierName;

    @Column(name = "Valid_From")
    private Date validFrom;

    @Column(name = "Valid_To")
    private Date validTo;

    @Column(name = "Active")
    private Boolean active;

    @Column(name = "Created_By", length = 255)
    private String createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Created_On")
    private Date createdOn;

    @Column(name = "Modified_By", length = 255)
    private String modifiedBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Modified_On")
    private Date modifiedOn;
    
    @OneToMany(mappedBy = "tenantMAWBRP", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TenantMAWBFreightRP> tenantMAWBFreightRPs;


//public void addChildRP(TenantMAWBFreightRP tenantMAWBFreightRP)
//{
//	
//
//}
}
