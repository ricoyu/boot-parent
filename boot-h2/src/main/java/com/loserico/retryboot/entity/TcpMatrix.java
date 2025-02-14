package com.loserico.retryboot.entity;

import com.loserico.retryboot.enums.Direction;
import com.loserico.retryboot.enums.EquipmentType;
import com.loserico.retryboot.enums.MessageType;
import lombok.Data;

import jakarta.persistence.Column;
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
@Table(name = "tcp_matrix")
public class TcpMatrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer seq;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EquipmentType equipmentType;

    @Column(length = 64, nullable = false)
    private String equipmentId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageType msgType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Direction direction;

    @Column(nullable = false)
    private Boolean ack;

    @Column(nullable = false)
    private Boolean received;

    @Column(length = 1024, nullable = false)
    private String msg;
    
    @Column(name = "reply_seq")
    private Integer replySeq;

    @Column(length = 64)
    private String creator;

    @Column(nullable = false, name = "create_time")
    private LocalDateTime createTime;

    @Column(length = 64)
    private String modifier;

    @Column(nullable = false, name = "modify_time")
    private LocalDateTime modifyTime;

}
