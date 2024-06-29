package com.loserico.es6.service.impl;

import com.loserico.cache.JedisUtils;
import com.loserico.es6.entity.ReadBookPd;
import com.loserico.es6.service.ElasticService;
import com.loserico.es6.service.ReadBookPdService;
import com.loserico.es6.service.ReindexService;
import com.loserico.es6.utils.RedisKey;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Copyright: (C), 2020/7/3 10:51
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Service
public class ReindexServiceImpl implements ReindexService {
	
	@Autowired
	private ReadBookPdService readBookPdService;
	
	@Autowired
	private ElasticService elasticService;
	
	@SneakyThrows
	@Override
	public boolean reindexBooks() {
		log.info("开始对books索引进行全量重建");
		/*String updateCore = JedisUtils.get(RedisKey.cacheKeys("cores", "update"));
		if (StringUtils.isBlank(updateCore)) {
			updateCore = "book1";
		}
		String currentCore = JedisUtils.get(RedisKey.cacheKeys("cores", "current"));
		if (StringUtils.isBlank(currentCore)) {
			currentCore = "book";
		}
		log.info("当前备份的索引集合为{}, 正在服务中的索引集合为{}", updateCore, currentCore);*/
		
		// 如果数据量达到上亿级那则需要引入大数据处理系统, hadoop, 进行离线索引重建
		/*int total = readBookPdService.getBookCount();
		int size = 100;
		int page = total / size;
		if (page > 100) {
			page = 100;
		}
		if (total % size != 0) {
			page++;
		}
		log.info("books数据库中记录为{}，按size={}，page={}", total, size, page);*/
		
		//ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 100, 100L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));
		//for (int i = 1; i <= page; i++) {
			//final int p = i;
			final String indexName = "book";
			//final String indexName = updateCore;
			//executorService.execute(() -> {
			//	List<ReadBookPd> bookPds = readBookPdService.getPageList(i, size);
				List<ReadBookPd> bookPds = readBookPdService.findAll();
				if (!bookPds.isEmpty()) {
					List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
					for (ReadBookPd bookPd : bookPds) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("bookId", bookPd.getId());
						map.put("bookName", bookPd.getName());
						map.put("bookEnName", bookPd.getEnName());
						map.put("author", bookPd.getAuthor());
						map.put("imgurl", bookPd.getImgurl());
						map.put("createTime", bookPd.getCreateTime().getTime());
						map.put("status", bookPd.getStatus());
						map.put("discription", bookPd.getDiscription());
						map.put("price", bookPd.getPrice());
						map.put("category", bookPd.getCategory());
						map.put("commentNum", bookPd.getCommentNum());
						//如果自己定义了score字段，这里就会有一个计算得分的模块
						//map.put("score","1231");
						datas.add(map);
					}
					log.info("查询完成, 一共{}条记录", bookPds.size());
					//批量操作
					elasticService.addBulkIn(datas, indexName, "_doc");
					//log.info("books的page={}索引重建成功", i);
				}
			//});
		//}
		
		//executorService.shutdown();
		//while (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
		//	log.info("等待索引重建完成.....");
		//}
		
		log.info("对books索引全量重建完成, 进行集合的切换");
		//JedisUtils.set(RedisKey.cacheKeys("cores", "update"), currentCore);
		//JedisUtils.set(RedisKey.cacheKeys("cores", "current"), updateCore);
		//log.info("切换成功,当前备份的索引集合为{}，正在服务中的索引集合为{}", currentCore, updateCore);
		
		return true;
	}
}
