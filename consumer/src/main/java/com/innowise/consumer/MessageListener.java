package com.innowise.consumer;

import com.innowise.producer.MessageProducer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;

public class MessageListener {

    private final Logger logger = LogManager.getLogger(MessageListener.class);

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "foo", containerFactory = "kafkaListenerContainerFactory")
    public void listener(String message) {
        logger.log(Level.DEBUG,"Recieved message: " + message);
    }
}
