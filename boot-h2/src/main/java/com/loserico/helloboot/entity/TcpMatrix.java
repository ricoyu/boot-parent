package com.loserico.helloboot.entity;

import com.loserico.helloboot.enums.Direction;
import com.loserico.helloboot.enums.EquipmentType;
import com.loserico.helloboot.enums.MessageType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
