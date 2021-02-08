package com.loserico.boot.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-10-19 15:51
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Data
@Document("sandboxmgr")
public class SandboxMgr{
	
	/**
	 * id
	 */
	@Id
	private String uuid;
	
	/**
	 * id
	 */
	private String name;
	/**
	 * 版本信息
	 */
	private String version;
	
	/**
	 * 主网卡的IPv4地址，若没有则取IPv6地址
	 */
	@Field("host_ip")
	private String hostIp;
	/**
	 * 沙盒状态  0-离线  1-在线
	 */
	private int state;
	
	/**
	 * CPU使用率
	 */
	private String cpu;
	
	/**
	 * number of processes
	 */
	private String procs;
	
	/**
	 * 内存总量，单位KB
	 */
	@Field("mem_total")
	private String memTotal;
	
	/**
	 * 已使用内存，单位KB
	 */
	@Field("mem_used")
	private String memUsed;
	
	/**
	 * 磁盘总量，单位KB
	 */
	@Field("disk_total")
	private String diskTotal;
	
	/**
	 * 已使用磁盘，单位KB
	 */
	@Field("disk_used")
	private String diskUsed;
	/**
	 * 网卡名称
	 */
	private String netcard;
	
	/**
	 * 网卡状态
	 */
	private String flags;
	
	/**
	 * 创建时间
	 */
	private Date ctime;
	
	/**
	 * 修改时间
	 */
	private Date utime;
	
	/**
	 * Ip地址列表
	 */
	private List<IpDto> ips;
	
	/**
	 * 抓包开关
	 */
	@Field("switch_flag")
	private Boolean switchFlag;
	
	/**
	 * 删除符
	 */
	private Boolean delflag;
	
	/**
	 * 机构
	 */
	@Field("organ_id")
	private String organId;
}
