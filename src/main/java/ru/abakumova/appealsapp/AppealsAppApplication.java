package ru.abakumova.appealsapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.abakumova.appealsapp.configs.RabbitConfiguration;

@SpringBootApplication
@Slf4j
//@EnableScheduling
@EnableAutoConfiguration(exclude={org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration.class})
public class AppealsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppealsAppApplication.class, args);
    }

}
