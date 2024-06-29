drop table if exists tcp_matrix;
create table tcp_matrix(
    id int not null auto_increment,
    seq int not null COMMENT '消息序号',
    equipment_type enum('TAMR','LIFT') not null COMMENT '设备类型',
    equipment_id varchar(64) not null COMMENT '设备ID',
    msg_type enum('ACK', 'MSG', 'LOG', 'REP', 'STR', 'STM', 'ERR') not null COMMENT '消息类型, 心跳报文不存储, 因为会很多且没什么必要',
    direction enum('IN','OUT') not null COMMENT '消息方向, in PLC --> RCS, out RCS --> PLC',
    ack tinyint not null default 0 COMMENT '是否需要被ACK',
    received tinyint not null default 0 COMMENT '是否已经被接收, 对应PLC --> RCS的报文, 1 表示RCS已经回复ACK报文给PLC, 对于RCS --> PLC的报文, 1 表示PLC已经回复ACK报文给RCS',
    msg varchar(1024) not null COMMENT '原始消息内容',
    reply_seq int COMMENT '消息可能是针对某条消息的回复, 这里记录被回复的消息序号',
    creator varchar(64) not null default '' COMMENT '创建者ID',
    create_time timestamp not null default current_timestamp COMMENT '创建时间',
    modifier varchar(64) not null default '' COMMENT '修改者ID',
    modify_time timestamp not null default current_timestamp on update current_timestamp COMMENT '修改时间',
    primary key(id),
    key idx_equipment_type_id(equipment_type, equipment_id),
    key idx_direction(direction),
    key idx_received(received),
    key ids_seq(seq),
    key idx_reply_seq(reply_seq)
);

drop table if exists tcp_task_status;
create table tcp_task_status
(
    id             int           not null auto_increment,
    seq            int           not null COMMENT '消息序号',
    equipment_type enum('TAMR','LIFT') not null COMMENT '设备类型',
    equipment_id   varchar(64)   not null COMMENT '设备ID',
    qty            int           not null COMMENT '子任务数量',
    finished_qty   int           not null COMMENT '已完成子任务数量',
    task_type      varchar(20)   not null default '' COMMENT '任务类型',
    position_id    varchar(50)   not null default '' COMMENT '当前位置',
    msg            varchar(1024) not null COMMENT '原始消息内容',
    creator        varchar(64)   not null default '' COMMENT '创建者ID',
    create_time    timestamp     not null default current_timestamp COMMENT '创建时间',
    modifier       varchar(64)   not null default '' COMMENT '修改者ID',
    modify_time    timestamp     not null default current_timestamp on update current_timestamp COMMENT '修改时间',
    command_id     varchar(20)   not null default '' COMMENT '最新完成的子任务指令',
    primary key (id),
    key idx_equipment_type_id(equipment_type, equipment_id)
)
