package com.loserico.fileupload.controller;

import com.loserico.common.lang.utils.IOUtils;
import com.loserico.web.utils.RestUtils;
import com.loserico.workbook.utils.ExcelUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * <p>
 * Copyright: (C), 2021-07-08 10:39
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("")
public class FileDownloadController {
	
	private AtomicInteger requestCount = new AtomicInteger(0);
	
	@SneakyThrows
	@GetMapping("/downloadFile")
	public ResponseEntity<Resource> downloadFile(HttpServletRequest request) {
		log.info("收到第{}个请求", requestCount.incrementAndGet());
		SECONDS.sleep(2);
		Resource resource = new FileSystemResource("D:\\Dropbox\\Dropbox.zip");
		// Try to determine file's content type
		String contentType = contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		
		// Fallback to the default content type if type could not be determined
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@SneakyThrows
	@GetMapping("/downloadFile2")
	public void downloadFile() {
		RestUtils.download(IOUtils.readClasspathFileAsFile("asset_group_template.xlsx"), "资产组数据.xlsx");
	}
	
	@GetMapping("/downloadFile3")
	public void downloadExcel() {
		Rebate rebate1 = Rebate.builder()
				.businessTime(LocalDateTime.now())
				.amount(BigDecimal.valueOf(100L))
				.quantityStr("66")
				.unitPrice(BigDecimal.valueOf(1L))
				.billType("人民币")
				.goodsDesc("球鞋")
				.purchaseLotNo("0001")
				.buyTime(new Date())
				.goodsNo("球鞋001")
				.supplierNo("supplier001")
				.billNo("bill001")
				.build();
		Rebate rebate2 = Rebate.builder()
				.businessTime(LocalDateTime.of(2020, 11, 1, 11, 01))
				.amount(BigDecimal.valueOf(100L))
				.quantityStr("66")
				.unitPrice(BigDecimal.valueOf(1L))
				.billType("美金")
				.goodsDesc("笔记本电脑")
				.purchaseLotNo("0002")
				.buyTime(new Date())
				.goodsNo("笔记本电脑002")
				.supplierNo("supplier003")
				.billNo("bill003")
				.build();
		List<Rebate> rebates = asList(rebate1, rebate2);
		Path path = ExcelUtils.write2Excel("rebate_detail_template.xls", 0, rebates);
		RestUtils.download(path.toFile(), "资产组数据.xls");
	}
	
	@Data
	@Builder
	private static class Rebate {
		
		private LocalDateTime businessTime;
		
		private BigDecimal amount;
		
		private String quantityStr;
		
		private BigDecimal unitPrice;
		
		private String billType;
		
		private String goodsDesc;
		
		private String purchaseLotNo;
		
		private Date buyTime;
		
		private String goodsNo;
		
		private String supplierNo;
		
		private String billNo;
		
	}
}
