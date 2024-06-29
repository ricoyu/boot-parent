package com.loserico.es6.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loserico.es6.entity.ReadBookPd;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Copyright: (C), 2020/7/3 9:27
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public interface ReadBookPdMapper extends BaseMapper<ReadBookPd> {
	
	public List<ReadBookPd> getPageList(int start, int size);
	
	public List<Map<String, Object>> buildESQuery(int id);
	
	public Page<ReadBookPd> listByPage(Page page);
}
