# 测试

## 创建Topic

```shell
kafka-topics.sh --create --zookeeper 192.168.100.104:2181 --replication-factor 1 --partitions 1 --topic streams-plaintext-input
```

Note: we create the output topic with compaction enabled because the output stream is a changelog stream

```shell
kafka-topics.sh --create --zookeeper 192.168.100.104:2181 --replication-factor 1 --partitions 1 --topic streams-wordcount-output --config cleanup.policy=compact
```

## 启动WordCountDemo程序

## 另起一个Console运行Producer

```shell
kafka-console-producer.sh --broker-list 192.168.100.104:9092 --topic streams-plaintext-input
```

## 另起一个Console运行Consumer

```
kafka-console-consumer.sh --bootstrap-server 192.168.100.104:9092 \
    --topic streams-wordcount-output \
    --from-beginning \
    --formatter kafka.tools.DefaultMessageFormatter \
    --property print.key=true \
    --property print.value=true \
    --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
    --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer
```



