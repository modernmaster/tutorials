package isb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageConsumerService {

    //, groupId = "outbound"
    @KafkaListener(topics = "testTopicOutput", groupId = "outbound")
    public void listenGroupFoo(String message) {
        //read message
        log.info("Received Message in group foo: " + message);

        //read from snowflake

        //transform if required

        //save to local store
        // playerRepository.save()
    }
}
