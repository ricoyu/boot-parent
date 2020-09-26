package com.loserico.bootjdbc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource.druid")
@Data
public class DruidDataSourceProperties {
	
	private String url;
	
	private String username;
	
	private String password;
	
	private Integer initialSize;
	
	private Integer minIdle;
    
	private Integer maxActive;
    
	private long maxWait;
	
    private String validationQuery;
    
    private Long validationQuerytimeout;
    
    private boolean testWhileIdle;
    
    private boolean testOnBorrow;
    
    private boolean testOnReturn;
    
    private boolean keepAlive;
    
    private Long timeBetweenEvictionRunsMillis;
    
    private Long minEvictableIdleTimeMillis;
    
	private boolean poolPreparedStatements;
    
    private Integer maxPoolPreparedStatementPerConnectionSize;
    
	public String filters;
	
	private String driverClassName;
}