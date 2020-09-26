package com.loserico.es6.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * Copyright: (C), 2020/7/2 21:35
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Data
@TableName("read_book_pd")
public class ReadBookPd {
	
	private Integer id;
	
	private String name;
	
	@TableField("en_name")
	private String enName;
	
	private String author;
	
	private String imgurl;
	
	@TableField("create_time")
	private Date createTime;
	
	private Integer creator;
	
	@TableField("update_time")
	private Date updateTime;
	
	@TableField("comment_num")
	private Integer commentNum;
	
	@TableField
	private Integer price;
	
	@TableField("category")
	private String category;
	
	/**
	 * 1正常，-1删除，0下架
	 */
	private Integer status;
	
	private String discription;
}
