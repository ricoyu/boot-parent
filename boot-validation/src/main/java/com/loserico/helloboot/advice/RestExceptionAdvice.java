package com.loserico.helloboot.advice;

import com.loserico.common.lang.vo.Result;
import com.loserico.common.lang.vo.Results;
import com.loserico.validation.bean.ErrorMessage;
import com.loserico.validation.exception.GeneralValidationException;
import com.loserico.validation.utils.ValidationUtils;
import com.loserico.web.utils.MessageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
@Slf4j
public class RestExceptionAdvice extends ResponseEntityExceptionHandler {
	
	private static Pattern messageTemplatePattern = Pattern.compile("\\{(.+)\\}");
	
	@Override
	@ResponseBody
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
	                                                    HttpStatusCode status, WebRequest request) {
		logger.error("Rest API ERROR happen", ex);
		return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	/**
	 * 表单提交数据校验错误，或者提交的数据转换成目标数据类型时候出错
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode status,
	                                                     WebRequest request) {
		logger.error("Rest API ERROR happen", ex);
		headers.add("Content-Type", "application/json");
		if (ex instanceof BindException e) {
			ErrorMessage errorMessage = ValidationUtils.getErrorMessage(e.getBindingResult());

			Result result = Results.status("400", errorMessage.getErrors()).build();
			return new ResponseEntity(result, headers, HttpStatus.OK);
		}
		return new ResponseEntity(null, headers, HttpStatus.OK);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	                                                              HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		logger.error("Rest API ERROR happen", ex);
		ErrorMessage errorMessage = ValidationUtils.getErrorMessage(ex.getBindingResult());
		List<String[]> msgs = errorMessage.getErrors()
				.stream()
				.map((errArray) -> {
					Matcher matcher = messageTemplatePattern.matcher(errArray[1]);
					if (matcher.matches()) {
						errArray[1] = MessageHelper.getMessage(matcher.group(1));
					}
					return errArray;
				})
				.collect(toList());
		Result result = Results.status("400", msgs).build();
		return new ResponseEntity(result, headers, HttpStatus.OK);
	}
	
	/**
	 * 手工验证不通过时抛出
	 *
	 * @param e
	 * @return ResponseEntity<Object>
	 */
	@ExceptionHandler(GeneralValidationException.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	protected ResponseEntity<Object> handleMethodArgumentNotValid(GeneralValidationException e) {
		logger.error("Rest API ERROR happen", e);
		ErrorMessage errorMessage = e.getErrorMessage();
		List<String[]> msgs = errorMessage.getErrors()
				.stream()
				.map((errArray) -> {
					Matcher matcher = messageTemplatePattern.matcher(errArray[1]);
					if (matcher.matches()) {
						errArray[1] = MessageHelper.getMessage(matcher.group(1));
					}
					return errArray;
				})
				.collect(toList());
		Result result = Results.status("400", msgs).build();
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
}
