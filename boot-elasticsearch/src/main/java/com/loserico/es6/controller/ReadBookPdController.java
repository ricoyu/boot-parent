package com.loserico.es6.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loserico.common.lang.vo.Result;
import com.loserico.common.lang.vo.Results;
import com.loserico.es6.entity.ReadBookPd;
import com.loserico.es6.service.ReadBookPdService;
import com.loserico.es6.service.ReindexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Copyright: (C), 2020/7/3 15:18
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
@RequestMapping("/read/book/pd")
public class ReadBookPdController {
	
	@Autowired
	private ReadBookPdService readBookPdService;
	
	@Autowired
	private ReindexService reindexService;
	
	@PostMapping
	public Result add(@RequestBody ReadBookPd readBookPd) {
		readBookPdService.save(readBookPd);
		return Results.success().build();
	}
	
	@PutMapping
	public Result update(@RequestBody ReadBookPd readBookPd) {
		readBookPdService.update(readBookPd);
		return Results.success().build();
	}
	
	@GetMapping("/{id}")
	public Result detail(@PathVariable Integer id) {
		ReadBookPd readBookPd = readBookPdService.findById(id);
		return Results.success().result(readBookPd);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Integer id) {
		readBookPdService.deleteById(id);
		return Results.success().build();
	}
	
	@GetMapping("/indexall")
	public Result indexAll() {
		reindexService.reindexBooks();
		return Results.success().build();
	}
	
	@GetMapping
	public Result list(Page page) {
		Page<ReadBookPd> result = readBookPdService.getPageList(page);
		return Results.success().result(result.getRecords());
	}
}
