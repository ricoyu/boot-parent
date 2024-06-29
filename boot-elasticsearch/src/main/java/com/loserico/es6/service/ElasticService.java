package com.loserico.es6.service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Copyright: (C), 2020/7/3 9:42
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public interface ElasticService {
	
	public boolean add(Map<String, Object> doc, String index, String type) throws Exception;
	
	public boolean adds(List<Map<String, Object>> docs);
	
	public boolean addBulkIn(List<Map<String, Object>> datas, String index, String type);
	
	public boolean delete(String index);
}
