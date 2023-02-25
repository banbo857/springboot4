package com.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig0226 {
       @Bean
       public Queue Queue() {
              return new Queue("hello");
       }
}
