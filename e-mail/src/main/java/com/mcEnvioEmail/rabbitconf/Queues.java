package com.mcEnvioEmail.rabbitconf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Queues {

    @Bean
    public Queue queueCreated() {
        return new Queue("solarSystem.v1.created");
    }

    @Bean
    public Queue queueError() {
        return new Queue("solarSystem.v1.error");
    }

    @Bean
    public Binding binding(){
        Queue queueCreated = new Queue("solarSystem.v1.created");
        FanoutExchange fanoutExchange = new FanoutExchange("solarSystem.v1");
        return BindingBuilder.bind(queueCreated).to(fanoutExchange);
    }

    @Bean
    public Binding binding2(){
        Queue queueError = new Queue("solarSystem.v1.error");
        FanoutExchange fanoutExchange = new FanoutExchange("solarSystem.v1");
        return BindingBuilder.bind(queueError).to(fanoutExchange);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory,
                                         Jackson2JsonMessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin admin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationEvent(
            RabbitAdmin admin
    ){
        return event -> {
            admin.initialize();
        };
    }
}
