package de.janlingen.userservice;

import de.janlingen.userservice.data.OutboxMessage;
import de.janlingen.userservice.data.OutboxRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author janlingen
 */
@Component
@RequiredArgsConstructor
public class OutboxTask {
  private final OutboxRepository outboxRepository;
  private final KafkaTemplate<String, String> kafkaTemplate;

  @Scheduled(fixedRate = 10000)
  public void scheduledTask(){
    List<OutboxMessage> outboxMessages = outboxRepository.findAll();
    for(OutboxMessage message : outboxMessages){
      kafkaTemplate.send(message.getZielService(), message.getInhalt());
      outboxRepository.deleteById(message.getId());
    }
  }
}
