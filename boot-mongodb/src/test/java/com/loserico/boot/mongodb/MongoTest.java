package com.loserico.boot.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Consumer;

import static com.loserico.json.jackson.JacksonUtils.toJson;

/**
 * <p>
 * Copyright: (C), 2020-7-27 0027 16:59
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
public class MongoTest {
	
	private MongoClient mongoClient = null;
	
	@Before
	public void testBefore() {
		mongoClient = new MongoClient(
				new ServerAddress("localhost", 27017),
				MongoCredential.createCredential("phantom", "phantom", "123456".toCharArray()),
				MongoClientOptions.builder().build());
	}
	
	/**
	 * 列出所有数据库, 需要连到admin数据库执行
	 */
	@Test
	public void testConnectAndListDBs() {
		//MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
		MongoClient mongoClient = new MongoClient(
				new ServerAddress("localhost", 27017),
				MongoCredential.createCredential("admin", "admin", "123456".toCharArray()),
				MongoClientOptions.builder().build());
		
		mongoClient.listDatabaseNames().forEach(new Consumer<String>() {
			@Override
			public void accept(String s) {
				System.out.println(s);
			}
		});
	}
	
	/**
	 * 插入数据
	 */
	@Test
	public void testInsert() {
		MongoDatabase database = mongoClient.getDatabase("phantom");
		MongoCollection<Document> emps = database.getCollection("emps");
		
		//BasicDBObject dbObject = new BasicDBObject();
		//dbObject.put("username", "sanshaoye");
		//dbObject.put("age", 28);
		//emps.insertOne(Document.parse(dbObject.toJson()));
		
		/*DBObject dbObject1 = BasicDBObjectBuilder.start()
				.add("姓名", "三少爷")
				.add("年龄", 28)
				.get();
		emps.insertOne(Document.parse(dbObject1.toString()));*/
		
		String json = "'姓名': 'Sexy Uncle', '年龄': 38";
		
	}
	
	@Test
	public void testFind() {
		MongoDatabase database = mongoClient.getDatabase("phantom");
		MongoCollection<Document> emps = database.getCollection("emps");
		
		FindIterable<Document> documents = emps.find();
		for (Document document : documents) {
			Object username = document.get("username");
			log.info("username: " + username);
			log.info("document: " + toJson(document));
			log.info("document2: " + document.toJson());
		}
	}
	
	@Test
	public void testAggregation() {
		MongoDatabase db = mongoClient.getDatabase("mydb");
	}
}
