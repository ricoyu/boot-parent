package com.loserico.es6.service;

/**
 * <p>
 * Copyright: (C), 2020/7/3 9:19
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public interface ReindexService {
	
	/**
	 * 对books索引进行全量重建
	 * @return
	 */
	public boolean reindexBooks();
}
