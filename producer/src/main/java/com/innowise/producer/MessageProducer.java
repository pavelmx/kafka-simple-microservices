package com.innowise.producer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class MessageProducer {

    private final Logger logger = LogManager.getLogger(MessageProducer.class);

    public MessageProducer() {
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${kafka.topic.name}")
    private String topicName;

    public void sendMessage(String message) {
        logger.log(Level.DEBUG, message);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.log(Level.DEBUG,"Unable to send message = " + message + " dut to: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> stringDataSendResult) {
                logger.log(Level.DEBUG,"Sent Message = " + message + " with offset = " + stringDataSendResult.getRecordMetadata().offset());
            }
        });
    }
}
