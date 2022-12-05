package ru.abakumova.appealsapp.email;

import org.springframework.kafka.core.KafkaTemplate;

public class Sender {
    private final KafkaTemplate<String, Object> template;

    public Sender(KafkaTemplate<String, Object> template) {
        this.template = template;
    }

    public void send(String topic, Object toSend) {
        this.template.send(topic, toSend);
    }

}
