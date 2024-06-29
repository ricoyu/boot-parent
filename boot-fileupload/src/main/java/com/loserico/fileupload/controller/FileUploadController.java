package com.loserico.fileupload.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * Copyright: (C), 2021-03-24 10:24
 * <p>
 * <p>
 * Company: Information & Data Security Solutions Co., Ltd.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@RestController
public class FileUploadController extends BaseController {
	
	@PostMapping("/upload")
	public String upload(HttpServletRequest request, MultipartFile file, Boolean switcher) {
		log.info(file == null ? null : file.getOriginalFilename());
		printHeaders(request);
		printParameters(request);
		return "done!";
	}
	
	@PostMapping("/form-submit")
	public String upload(Boolean switcher) {
		log.info("" + switcher);
		return "done!";
	}
}
