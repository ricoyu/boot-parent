package com.loserico.boot.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-08-20 10:18
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("person")
public class Person {
	
	@Id
	private String personId;
	private String name;
	private long age;
	private List<String> favoriteBooks;
	private LocalDate dateOfBirth;
	
	@Override
	public String toString() {
		return String.format("Person{personId='%s', name='%s', age=%d, dateOfBirth=%s}\n",
				personId, name, age, dateOfBirth);
	}
}
