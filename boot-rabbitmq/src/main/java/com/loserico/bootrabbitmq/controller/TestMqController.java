package com.loserico.bootrabbitmq.controller;

import com.loserico.bootrabbitmq.producer.LordthreeProductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestMqController {

    @Autowired
    private LordthreeProductor lordthreeProductor;
    
    @RequestMapping("/msg-direct/{msg}")
    public String testMqSender(@PathVariable("msg") String msg) {
        lordthreeProductor.sendMsgDirect(msg);
        return "OK";
    }

    @RequestMapping("/msg-fanout/{msg}")
    public String testFanoutMqSender(@PathVariable("msg") String msg) {
        lordthreeProductor.sendMsgFanout(msg);
        return "OK";
    }

    @RequestMapping("/msg-topic/{msg}")
    public String testTopicSender(@PathVariable("msg") String msg) {
        lordthreeProductor.sendMsgTopic(msg);
        return "OK";
    }
}