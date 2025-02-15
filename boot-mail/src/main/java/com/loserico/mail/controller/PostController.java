package com.loserico.mail.controller;

import com.loserico.common.lang.vo.Result;
import com.loserico.common.lang.vo.Results;
import com.loserico.mail.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.loserico.json.jackson.JacksonUtils.toPrettyJson;

@Slf4j
@RestController
@RequestMapping("")
public class PostController {

    @PostMapping("/asstr")
    public String post(@RequestBody String payload) {
        log.info("收到提交的数据: {}", payload);
        return "ok";
    }

    @PostMapping("/asuser")
    public Result user(@RequestBody UserVO user) {
        String prettyJson = toPrettyJson(user);
        System.out.println(prettyJson);

        return Results.success().build();
    }

}
