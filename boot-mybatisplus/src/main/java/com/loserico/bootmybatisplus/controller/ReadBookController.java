package com.loserico.bootmybatisplus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loserico.bootmybatisplus.entity.ReadBook;
import com.loserico.bootmybatisplus.service.ReadBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Copyright: (C), 2020/6/30 17:47
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
@RequestMapping("/read-book")
public class ReadBookController {
	
	@Autowired
	private ReadBookService readBookService;
	
	@PostMapping("/search")
	public Page<ReadBook> queryReadBook(String name, Page page) {
		return readBookService.queryBookByName(name, page);
	}
}
