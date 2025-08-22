package com.nyl.kafka.service;

import com.nyl.kafka.model.Message;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"demo-topic"})
class KafkaConsumerServiceTest {

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    // We’ll capture consumed message in a thread-safe holder
    private static final AtomicReference<Message> consumedMessage = new AtomicReference<>();

    // Modify the consumer to store consumed message for test
    // (instead of only printing)
    @Autowired
    public void setTestHook(KafkaConsumerService consumer) {
        consumer.setConsumerHook(consumedMessage::set);
    }

    private KafkaTemplate<String, Message> kafkaTemplate() {
        Map<String, Object> senderProps = KafkaTestUtils.producerProps("localhost:9092");
        senderProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        senderProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, org.springframework.kafka.support.serializer.JsonSerializer.class);
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(senderProps));
    }

    @Test
    void testKafkaConsumerReceivesMessage() throws Exception {
        KafkaTemplate<String, Message> template = kafkaTemplate();

        Message testMessage = new Message();
        testMessage.setContent("Hello Kafka Test!");

        template.send("demo-topic", testMessage);

        // Wait for message to be consumed
        TimeUnit.SECONDS.sleep(2);

        assertThat(consumedMessage.get()).isNotNull();
        assertThat(consumedMessage.get().getContent()).isEqualTo("Hello Kafka Test!");
    }
}
