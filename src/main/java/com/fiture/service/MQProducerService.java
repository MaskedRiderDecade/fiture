package com.fiture.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Destination;

@Service(value = "MQProducerService")
public class MQProducerService {
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    public void send(String destinationName ,String message){
        System.out.println("------------ 发送消息"+ message);
        Destination destination = new ActiveMQQueue(destinationName);
        jmsMessagingTemplate.convertAndSend(destination,message);
    }
}
