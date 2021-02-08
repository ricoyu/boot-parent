package com.loserico.yellingappdatagenerator;

import com.loserico.common.lang.utils.IOUtils;
import com.loserico.yellingappdatagenerator.producer.MockDataProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class YellingAppDataGeneratorApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(YellingAppDataGeneratorApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Enter command: ");
		System.out.println("PurchaseData: 1");
		
		
		IOUtils.readCommandLine((s) -> {
			Runnable startTask = null;
			Runnable stopTask = null;
			
			if ("1".equals(s)) {
				//生成数据丢到kafka中
				MockDataProducer.producePurchaseData();
				startTask = () -> MockDataProducer.startProducePurchaseData();
				stopTask = () -> MockDataProducer.stopProducePurchaseData();
				startTask.run();
			} else if ("start".equals(s)) {
				startTask.run();
			} else if ("stop".equals(s)) {
				stopTask.run();
			}
		});
	}
}
