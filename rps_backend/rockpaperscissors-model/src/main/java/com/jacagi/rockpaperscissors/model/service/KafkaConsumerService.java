package com.jacagi.rockpaperscissors.model.service;

import java.util.Date;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.jacagi.rockpaperscissors.api.entity.GameMovesEntity;
import com.jacagi.rockpaperscissors.api.repository.GameMovesRepository;

@Service
public class KafkaConsumerService {

	@Autowired
	private GameMovesRepository gameMovesRepository;
	
    @KafkaListener(topics = "rps_topic", groupId = "my-consumer-group")
    public void consume(ConsumerRecord<String, String> record) {
        System.out.println("Received message: " + record.value() + " from partition: " + record.partition());
        this.gameMovesRepository.save(new GameMovesEntity(record.value(), new Date()));
    }
	
}
