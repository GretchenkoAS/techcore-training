package com.example.demo.outbox;

import com.example.demo.domen.OutboxEvent;
import com.example.demo.repository.OutboxRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OutboxProcessor {

    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OutboxProcessor(OutboxRepository outboxRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.outboxRepository = outboxRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 2000)
    @Transactional
    public void processOutbox() {
        List<OutboxEvent> events =
                outboxRepository.findTop50ByProcessedFalseOrderByIdAsc();

        for(OutboxEvent outboxEvent : events) {
            try {
                kafkaTemplate.send(
                        "book_events",
                        outboxEvent.getAggregateId().toString(),
                        outboxEvent.getPayload()
                ).get();

                outboxEvent.setProcessed(true);
                outboxRepository.save(outboxEvent);
            } catch (Exception ex) {
                break;
            }
        }
    }
}
