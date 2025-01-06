package covy.springkafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

    /**
     * kafka topic Test를 위한 사용자(listener에서 값을 받을 수 있게 설정
     *
     * @param data
     */
    @KafkaListener(topics = "topic", groupId = "group_1")
    public void listener(Object data) {
        System.out.println(data);
    }

}
