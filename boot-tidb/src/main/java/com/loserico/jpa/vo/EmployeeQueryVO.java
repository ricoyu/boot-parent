package com.loserico.jpa.vo;

import com.loserico.common.lang.vo.Page;
import lombok.Data;

/**
 * <p>
 * Copyright: (C), 2023-10-02 20:49
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Data
public class EmployeeQueryVO {
	
	private String fullName;
	
	private Page page;
	
	private Double lowSalary;
	
	private Double highSalary;
}
