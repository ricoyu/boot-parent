package com.loserico.bootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loserico.bootmybatisplus.entity.ReadBook;

/**
 * <p>
 * Copyright: (C), 2020/6/30 17:35
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public interface ReadBookMapper extends BaseMapper<ReadBook> {
	Page<ReadBook> queryBookByName(String name, Page<?> page);
}
