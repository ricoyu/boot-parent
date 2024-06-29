package com.loserico.fileupload.advice;

import com.loserico.common.lang.vo.Result;
import com.loserico.common.lang.vo.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>
 * Copyright: (C), 2021-03-24 13:45
 * <p>
 * <p>
 * Company: Information & Data Security Solutions Co., Ltd.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MultipartException.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Object> handleFileUploadFailure(MultipartException e, RedirectAttributes attributes) {
		String message = e.getCause().getMessage();
		log.info(message);
		
		log.error("Rest API ERROR happen", e);
		Result result = Results.status("400", message).build();
		return new ResponseEntity(result, HttpStatus.OK);
	}
}
