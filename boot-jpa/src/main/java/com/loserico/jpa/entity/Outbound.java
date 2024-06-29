package com.loserico.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "outbound")
@IdClass(Outbound.OutboundId.class)
public class Outbound {

    @Embeddable
    @Data
    public static class OutboundId implements Serializable {
        @Column(name = "ORDER_ID")
        private String orderId;

        @Column(name = "DD_TENANT_ID")
        private String ddTenantId;
    }
    
    @Id
    @Column(name = "ORDER_ID")
    private String orderId;
    
    @Id
    @Column(name = "DD_TENANT_ID")
    private String ddTenantId;

    @Column(name = "SOURCE_ORDER_ID")
    private String sourceOrderId;

    @Column(name = "STATUS_ID")
    private String statusId = "CHECKED";

    @Column(name = "STATUS_NAME")
    private String statusName = "已执行";

    @Column(name = "BILL_TYPE_ID")
    private String billTypeId = "CrossOutBound";

    @Column(name = "BILL_TYPE_NAME")
    private String billTypeName = "普通出库";

    @Column(name = "WARE_HOUSE_ID")
    private String wareHouseId = "10000";

    @Column(name = "WARE_HOUSE_NAME")
    private String wareHouseName = "智能仓";

    @Column(name = "QTY", precision = 18, scale = 2)
    private BigDecimal qty;

    @Column(name = "ITEM_QTY", precision = 18, scale = 2)
    private BigDecimal itemQty;

    @Column(name = "NO_FINISHED_QTY", precision = 18, scale = 2)
    private BigDecimal noFinishedQty;

    @Column(name = "NO_FINISHED_ITEM", precision = 18, scale = 2)
    private BigDecimal noFinishedItem;

    @Column(name = "MEMO")
    private String memo;

    @Column(name = "CUSTOMER_ID")
    private String customerId = "10270";

    @Column(name = "CUSTOMER_NAME")
    private String customerName = "双宇sy2";

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "LINK_MAN")
    private String linkMan;

    @Column(name = "PHONE")
    private String phone;

    @Temporal(TemporalType.DATE)
    @Column(name = "WUYONG_DATE")
    private Date wuyongDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATED_STAMP")
    private Date lastUpdatedStamp = new Date();

    @Column(name = "CREATED_USER_ID")
    private String createdUserId = "10290";

    @Column(name = "LAST_UPDATED_USER_ID")
    private String lastUpdatedUserId = "10290";

    @Column(name = "CREATED_USER_NAME")
    private String createdUserName = "heshuyu";

    @Column(name = "LAST_UPDATED_USER_NAME")
    private String lastUpdatedUserName = "heshuyu";

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATED_TX_STAMP")
    private Date lastUpdatedTxStamp = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_STAMP")
    private Date createdStamp = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TX_STAMP")
    private Date createdTxStamp = new Date();

    @Column(name = "FINISHED_QTY", precision = 18, scale = 2)
    private BigDecimal finishedQty;
    
    @Column(name = "FINISHED_ITEM", precision = 18, scale = 2)
    private BigDecimal finishedItem;
    
    @Column(name = "SOURCE")
    private String source = "0";
    
    @Column(name = "STORAGE_ID")
    private String storageId;
    
    @Column(name = "STORAGE_NAME")
    private String storageName;
    
    @Column(name = "PRINTCOUNT")
    private BigDecimal printCount;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXPIRE_DATE")
    private Date expireDate = new Date();
    
    @Column(name = "SOURCE_FLG")
    private String sourceFlg;
    
    @Column(name = "SKU_NAME")
    private String skuName;
    
    @Column(name = "SKU_ID")
    private String skuId;
    
    @Column(name = "SOURCE_ORDER_MODE")
    private String sourceOrderMode;
    
    @Column(name = "CUSTOMER_ORDER_ID")
    private String customerOrderId;
    
    @Column(name = "UUID")
    private String uuid;
    
    @Column(name = "PROCESS_QTY", precision = 18, scale = 2)
    private BigDecimal processQty;
    
    @Column(name = "BUSINESS_SYSTEM_ID")
    private String businessSystemId;
    
    @Column(name = "OUTBOUND_TYPE_ID")
    private String outboundTypeId;
    
    @Column(name = "OUTBOUND_TYPE_NAME")
    private String outboundTypeName;

}


