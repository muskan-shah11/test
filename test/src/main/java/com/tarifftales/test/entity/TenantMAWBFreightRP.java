package com.tarifftales.test.entity;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tenant_mawb_freight_rp")
public class TenantMAWBFreightRP {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 255, nullable = false)
    private String id;

//    @OneToMany
//    @JoinColumn(name = "Charge_ID", referencedColumnName = "Charge_ID")
//    private String chargeId;

    @Column(name = "Carrier_Name", length = 255)
    private String carrierName;

    @Column(name = "Origin_Port", length = 255)
    private String originPort;

    @Column(name = "Dest_Port", length = 255)
    private String destPort;

    @Column(name = "Via_Port", length = 255)
    private String viaPort;

    @Column(name = "Frequency")
    private Integer frequency;

    @Column(name = "Transit_Time")
    private Integer transitTime;

    @Column(name = "Cargo_Mode", length = 255)
    private String cargoMode;

    @Column(name = "Cargo_Type", length = 255)
    private String cargoType;

    @Column(name = "Applicable_Flights", length = 255)
    private String applicableFlights;

    @Column(name = "Exception_Flights", length = 255)
    private String exceptionFlights;

    @Column(name = "ETD_From")
    private LocalTime etdFrom;

    @Column(name = "ETD_To")
    private LocalTime etdTo;

    @Column(name = "ETA")
    private LocalTime eta;

    @Column(name = "Active")
    private Boolean active;

    @Column(name = "Created_By", length = 255)
    private String createdBy;

    
    @CreatedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "Created_On")
    private Date createdOn;

    @Column(name = "Modified_By", length = 255)
    private String modifiedBy;

    @CreatedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "Modified_On")
    private Date modifiedOn;

    @Column(name = "Min_Val")
    private Integer minVal;

    @Column(name = "NRate")
    private Integer nRate;

    @Column(name = "Currency", length = 100)
    private String currency;

    @Column(name = "tax")
    private Integer tax;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Charge_ID", referencedColumnName = "Charge_ID", foreignKey = @ForeignKey(name = "Charge_ID"))
    private TenantMAWBRP tenantMAWBRP;
    
    @OneToMany(mappedBy = "tenantMAWBFreightRP", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TenantMAWBFreightRPRate> freightRPRateList;
    
    
    
}
