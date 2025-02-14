package com.loserico.retryboot.controller;

import com.loserico.common.lang.vo.Result;
import com.loserico.common.lang.vo.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest")
public class PostController {

    @PostMapping("/")
    public Result post(@RequestBody String payload) {
        log.info("收到提交的数据: {}", payload);
        return Results.success().build();
    }
}
