package com.image.googlecloudimageservice.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is configuration class helps to declare rabbimq configuration.
 */
@Configuration
public class MessagingConfig{

    public static final String QUEUE       = "image_queue";
    public static final String EXCHANGE    = "image_exchange";
    public static final String ROUTING_KEY = "image_routingKey";

    @Bean
    public Queue queue(){
        return new Queue(MessagingConfig.QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(MessagingConfig.EXCHANGE);
    }

    @Bean
    public Binding binding(final Queue queue, final TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(MessagingConfig.ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(final ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(this.converter());
        return rabbitTemplate;
    }
}