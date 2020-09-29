package com.loserico.yellingappdatagenerator;

import com.loserico.common.lang.utils.IOUtils;
import com.loserico.yellingappdatagenerator.producer.MockDataProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YellingAppDataGeneratorApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(YellingAppDataGeneratorApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		MockDataProducer.produceRandomTextData();
		IOUtils.readCommandLine((s) -> {
			if ("gen".equals(s)) {
				//生成数据丢到kafka中
				MockDataProducer.start();
			} else if ("shutdown".equals(s)) {
				MockDataProducer.shutdown();
			}
		});
	}
}
