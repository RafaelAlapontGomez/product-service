package com.rafael.product.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQPedidoStockErrorConfig {
   @Value("${jsa.rabbitmq.queue.error}")
   private String queueName;
   
   @Value("${jsa.rabbitmq.exchange.error}")
   private String exchange;
   
   @Value("${jsa.rabbitmq.routingkey.error}")
   private String routingKey;
   
   @Bean
   Queue queueError() {
      return new Queue(queueName, false);
   }

   @Bean
   DirectExchange exchangeError() {
      return new DirectExchange(exchange);
   }

   @Bean
   Binding bindingError(Queue queueError, DirectExchange exchangeError) {
      return BindingBuilder.bind(queueError).to(exchangeError).with(routingKey);
   }
   
//   @Bean
//   public MessageConverter jsonMessageConverter() {
//      return new Jackson2JsonMessageConverter();
//   }
}
