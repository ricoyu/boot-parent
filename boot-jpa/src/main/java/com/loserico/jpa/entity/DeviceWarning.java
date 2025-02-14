package com.loserico.jpa.entity;

import com.loserico.jpa.converter.EquipmentTypeConverter;
import com.loserico.jpa.enums.EquipmentType;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "device_warning")
public class DeviceWarning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 设备类型
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "equipment_type", nullable = false)
    @Convert(converter = EquipmentTypeConverter.class)
    private EquipmentType equipmentType;

    /**
     * 设备ID
     */
    @Column(name = "equipment_id", nullable = false, length = 64)
    private String equipmentId;
    
    /**
     * 告警代码, 一个int型的数字可以表示很多种告警状态, 这个数字是告警状态的组合, 用VehicleWarningAddressEnum的bitLocator按位或得到, 
     * 比如产生了这两个告警: MAINTENANCE_ACTIVED(2, 1 << 1, 40.1, "维修模式激活"),X_AXIS_BRAKE_RELEASED(3, 1 << 2, 40.2, "行走轴抱闸释放")
     * 那么告警代码alterCode就是: 1 << 1 | 1 << 2 = 6
     *
     * 然后执行SQL查询维修模式激活的告警:
     * SELECT * FROM alerts WHERE alert_code & 1<<1;
     * 执行更新来清除维修模式激活的告警:
     * UPDATE alerts SET alert_code = alert_code & ~(1<<1);
     */
    @Column(name = "alter_code", nullable = false)
    private Integer alterCode;

    /**
     * 创建者ID
     */
    @Column(name = "creator", nullable = false, length = 64)
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    /**
     * 修改者ID
     */
    @Column(name = "modifier", nullable = false, length = 64)
    private String modifier;

    /**
     * 修改时间
     */
    @Column(name = "modify_time", nullable = false)
    private LocalDateTime modifyTime;

}
