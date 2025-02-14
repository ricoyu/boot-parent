package com.loserico.retryboot.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * <p>
 * Copyright: (C), 2020-11-10 16:12
 * <p>
 * <p>
 * Company: Information & Data Security Solutions Co., Ltd.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("")
public class FastDfsController {
	
	@Autowired
	private TrackerClient trackerClient;
	
	/*@Autowired
	private GenerateStorageClient generateStorageClient;*/
	
	@Autowired
	private FastFileStorageClient fastFileStorageClient;
	
	@Autowired
	private AppendFileStorageClient appendFileStorageClient;
	
	@SneakyThrows
	@CrossOrigin
	@PostMapping("/upload")
	public String upload(MultipartFile file) {
		StorePath storePath = fastFileStorageClient.uploadFile("group1", file.getInputStream(), file.getSize(), "tar.gz");
		return storePath.getPath();
	}
	
	@SneakyThrows
	@CrossOrigin
	@PostMapping("/download")
	public File download(String path) {
		File file = fastFileStorageClient.downloadFile("group1", path, null);
		return file;
	}
}
