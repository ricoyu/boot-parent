package com.loserico.es6.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loserico.es6.entity.ReadBookPd;

import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020/7/3 9:22
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public interface ReadBookPdService {
	
	public void save(ReadBookPd readBookPd);
	
	public int getBookCount();
	
	public void deleteById(Integer id);
	
	public List<ReadBookPd> getPageList(int page, int size);
	
	public Page<ReadBookPd> getPageList(Page page);
	
	public void update(ReadBookPd readBookPd);
	
	public ReadBookPd findById(Integer id);
	
	public List<ReadBookPd> findAll();
	
}
