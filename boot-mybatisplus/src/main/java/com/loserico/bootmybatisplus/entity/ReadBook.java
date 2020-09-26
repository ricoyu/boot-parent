package com.loserico.bootmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * Copyright: (C), 2020/6/30 17:26
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Data
@TableName("read_book_pd")
public class ReadBook {
	
	private Long id;
	
	private String name;
	
	private String enName;
	
	private String author;
	
	private String category;
	
	@TableField("imgurl")
	private String imgUrl;
	
	private String description;
	
	/**
	 * 1正常，-1删除，0下架
	 */
	private int status;
	
	/**
	 * 评论数
	 */
	private Integer commentNum;
	
	/**
	 * 价格，分
	 */
	private int price;
	
	private String creator;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;
}
