//package com.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//
//@Configuration
//public class RabbitConfig {
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Value("${spring.rabbitmq.host}")
//    private String host;
//
//    @Value("${spring.rabbitmq.port}")
//    private int port;
//
//    @Value("${spring.rabbitmq.username}")
//    private String username;
//
//    @Value("${spring.rabbitmq.password}")
//    private String password;
//
//
//    public static final String EXCHANGE_A = "my-mq-exchange_A";
//    public static final String EXCHANGE_B = "my-mq-exchange_B";
//    public static final String EXCHANGE_C = "my-mq-exchange_C";
//
//
//    public static final String QUEUE_A = "QUEUE_A";
//    public static final String QUEUE_B = "QUEUE_B";
//    public static final String QUEUE_C = "QUEUE_C";
//
//    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
//    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";
//    public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
//        connectionFactory.setUsername(username);
//        connectionFactory.setPassword(password);
//        connectionFactory.setVirtualHost("/");
//        connectionFactory.setPublisherConfirms(true);
//        return connectionFactory;
//    }
//
//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    //必须是prototype类型
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory());
//        return template;
//    }
//}
