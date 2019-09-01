package com.rafael.product.rabbitmq.publisher;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rafael.product.rabbitmq.messages.PedidoStockError;

@Component
public class StockErrorPublisher {
	   @Autowired
	   private AmqpTemplate amqpTemplate;
	   
	   @Value("${jsa.rabbitmq.exchange.error}")
	   private String exchange;
	   
	   @Value("${jsa.rabbitmq.routingkey.error}")
	   private String routingKey;
	   
	   public void produceMsg(PedidoStockError pedidoStockError){
	      amqpTemplate.convertAndSend(exchange, routingKey, pedidoStockError);
	      System.out.println("Send msg = " + pedidoStockError);
	   }
}
