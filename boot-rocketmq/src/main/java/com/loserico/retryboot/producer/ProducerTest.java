package com.loserico.retryboot.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

public class ProducerTest {

    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("group0");
        producer.setNamesrvAddr("47.100.125.240:9876");
        try {
            producer.start();
            for (int i = 0; i < 10; i++) {
                Message msg = new Message("canal_equipment_topic", "tagA", "", ("hello"+i).getBytes(StandardCharsets.UTF_8));
                SendResult sendResult = producer.send(msg);
                //通过sendResult检查消息是否成功送达
                System.out.printf("%s%n", sendResult);
            }
            producer.shutdown();
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
