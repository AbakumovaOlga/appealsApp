package ru.abakumova.appealsapp.configs;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import ru.abakumova.appealsapp.models.enums.AppealStatus;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Value(value = "${spring.kafka.server}")
    private String kafkaServer;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topicAccepted() {
        return new NewTopic(AppealStatus.ACCEPTED.name(), 1, (short) 1);
    }

    @Bean
    public NewTopic topicRejected() {
        return new NewTopic(AppealStatus.REJECTED.name(), 1, (short) 1);
    }

}
