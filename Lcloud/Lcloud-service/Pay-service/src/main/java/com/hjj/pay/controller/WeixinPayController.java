package com.hjj.pay.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.hjj.pay.model.PayLog;
import com.hjj.util.RespResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/wx")
@CrossOrigin
public class WeixinPayController {

    private final RocketMQTemplate rocketMQTemplate;

    public WeixinPayController(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    @GetMapping("/result")
    public RespResult result() {
        PayLog payLog = new PayLog(IdWorker.getIdStr(), 1, "hello!", "AAA", new Date());
        // 构建消息
        Message<String> message = MessageBuilder.withPayload(JSON.toJSONString(payLog)).build();
        // 发送消息
        TransactionSendResult result = rocketMQTemplate.sendMessageInTransaction("rocket", "log", message, null);

        return RespResult.ok(result);
    }
}
