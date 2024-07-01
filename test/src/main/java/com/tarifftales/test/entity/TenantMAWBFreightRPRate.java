package com.tarifftales.test.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tenant_mawb_freight_rp_rate")
public class TenantMAWBFreightRPRate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 255, nullable = false)
    private String id;

//    @Column(name = "Charge_ID", length = 255)
//    private String chargeId;

//    @Column(name = "Tenant_MAWB_Freight_RP_ID", length = 255)
//    private String tenantMAWBFreightRPId;

    @Column(name = "Currency_CD", length = 255)
    private String currencyCd;

    @Column(name = "Weight_Slab_Type", length = 255)
    private String weightSlabType;

    @Column(name = "Freight_Value")
    private Integer freightValue;

    @Column(name = "Created_By", length = 255)
    private String createdBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "Created_On")
    private Date createdOn;

    @Column(name = "Modified_By", length = 255)
    private String modifiedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "Modified_On")
    private Date modifiedOn;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "Charge_ID", referencedColumnName = "Charge_ID", foreignKey = @ForeignKey(name = "Charge_ID"))
    private TenantMAWBRP tenantMAWBRP;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "Tenant_MAWB_Freight_RP_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "Tenant_MAWB_Freight_RP_ID"))
    private TenantMAWBFreightRP tenantMAWBFreightRP;
}

