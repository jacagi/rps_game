package com.jacagi.rockpaperscissors_producer.batch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jacagi.rockpaperscissors_producer.enums.RockPaperScissorsEnum;
import com.jacagi.rockpaperscissors_producer.service.KafkaProducerService;

@Service
public class BatchJobService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private KafkaProducerService kafkaProducerService;
    
    @Scheduled(cron = "${batch.job.cron}")
    public void runBatchJob() {
        System.out.println("Batch job started at: " + FORMATTER.format(LocalDateTime.now()));

        List<RockPaperScissorsEnum> rpsList = Arrays.asList(RockPaperScissorsEnum.values());
        
        this.kafkaProducerService.sendMessage("rps_topic", rpsList.get(ThreadLocalRandom.current().nextInt(rpsList.size())).name());

        System.out.println("Batch job finished at: " + FORMATTER.format(LocalDateTime.now()));
    }
	
}
