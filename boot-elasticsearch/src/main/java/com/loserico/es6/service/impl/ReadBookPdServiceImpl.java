package com.loserico.es6.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loserico.es6.entity.ReadBookPd;
import com.loserico.es6.mapper.ReadBookPdMapper;
import com.loserico.es6.service.ReadBookPdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020/7/3 9:31
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Service
public class ReadBookPdServiceImpl implements ReadBookPdService {
	
	@Autowired
	private ReadBookPdMapper readBookPdMapper;
	
	@Override
	public void save(ReadBookPd readBookPd) {
		readBookPdMapper.insert(readBookPd);
	}
	
	@Override
	public void deleteById(Integer id) {
		readBookPdMapper.deleteById(id);
	}
	
	@Override
	public void update(ReadBookPd readBookPd) {
		readBookPdMapper.updateById(readBookPd);
	}
	
	@Override
	public ReadBookPd findById(Integer id) {
		return readBookPdMapper.selectById(id);
	}
	
	@Override
	public List<ReadBookPd> findAll() {
		return readBookPdMapper.selectList(null);
	}
	
	@Override
	public int getBookCount() {
		QueryWrapper<ReadBookPd> query = new QueryWrapper<ReadBookPd>().eq("status", 1);
		return readBookPdMapper.selectCount(query);
	}
	
	@Override
	public List<ReadBookPd> getPageList(int page, int size) {
		return readBookPdMapper.getPageList(page, size);
	}
	
	@Override
	public Page<ReadBookPd> getPageList(Page page) {
		return readBookPdMapper.listByPage(page);
	}
}
