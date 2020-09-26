package com.loserico.bootproperties.config;

import com.loserico.common.lang.utils.DateUtils;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * <p>
 * Copyright: (C), 2020/4/10 13:02
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Component
@ConfigurationPropertiesBinding
public class DateTimeFormaterConverter implements Converter<String, LocalDate> {
	
	@Override
	public LocalDate convert(String source) {
		return DateUtils.toLocalDate(source);
	}
}
