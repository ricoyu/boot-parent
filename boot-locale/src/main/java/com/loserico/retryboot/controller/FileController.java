package com.loserico.retryboot.controller;

import com.loserico.common.lang.utils.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * <p>
 * Copyright: (C), 2020-09-22 15:28
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
public class FileController {
	
	@GetMapping("/files")
	public int files(String dir, String filePattern) throws IOException {
		//List<String> files = IOUtils.listClasspathFileNames(dir, filePattern);
		/*List<String> contents = files.stream().map((file) -> {
			return IOUtils.readFileAsString(file);
		}).collect(toList());*/
		byte[] bytes = IOUtils.readClassPathFileAsBytes("aggregate.json");
		return bytes.length;
	}
}
