package com.loserico.bootmybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loserico.bootmybatisplus.entity.ReadBook;
import com.loserico.bootmybatisplus.mapper.ReadBookMapper;
import com.loserico.bootmybatisplus.service.ReadBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Copyright: (C), 2020/6/30 17:36
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Service
public class ReadBookServiceImpl implements ReadBookService {
	
	@Autowired
	private ReadBookMapper readBookMapper;
	
	@Override
	public Page<ReadBook> queryBookByName(String name, Page<ReadBook> page) {
		// 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
		// page.setOptimizeCountSql(false);
		// 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
		// 要点!! 分页返回的对象与传入的对象是同一个
		//return readBookMapper.queryBookByName(name, page);
		return readBookMapper.selectPage(page, null);
	}
}
