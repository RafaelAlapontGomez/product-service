package com.rafael.product.rabbitmq.subcribers;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rafael.product.rabbitmq.messages.PedidoStockError;
import com.rafael.product.rabbitmq.messages.PedidoStockMessage;
import com.rafael.product.rabbitmq.publisher.StockErrorPublisher;
import com.rafael.product.services.HandleStockPedidoMessage;
import com.rafael.product.services.exceptions.InsuficienteStokException;
import com.rafael.product.services.exceptions.ProductNotExistException;
import com.rafael.product.services.impl.HandleStockPedidoMessageImpl;
import com.rafael.product.services.impl.ProductCommandServiceImpl;

@Component
public class PedidoSubscriber {
	@Autowired
	HandleStockPedidoMessageImpl handleStock;
	
	@Autowired
	StockErrorPublisher stockErrorPubliser;
	
	@Autowired
	ProductCommandServiceImpl productService;
	
   	@SuppressWarnings("unchecked")
	@RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void receivedMessage(PedidoStockMessage msg) {
        System.out.println("Received Message: " + msg);
        @SuppressWarnings("rawtypes")
		HashMap<String, Optional> valorDevuelto = null;
        
        @SuppressWarnings("unused")
		Double total = 0.0;
        HashMap<Long, Long> newStock = new HashMap<>();
        try {
        	valorDevuelto = handleStock.validarStock(msg.getItems());
       		total = (Double) valorDevuelto.get(HandleStockPedidoMessage.TOTAL).get();
       		newStock = (HashMap<Long, Long>) valorDevuelto.get(HandleStockPedidoMessage.STOCK).get();
            productService.updateStockPedido(newStock);
		} catch (ProductNotExistException ex) {
	        System.out.println("Exception: " + ex.getMessage());
			stockErrorPubliser.produceMsg(buidPedidoError(msg.getUuid(), msg.getPedidoId(), ex.getMessage()));
		} catch (InsuficienteStokException ex) {
	        System.out.println("Exception: " + ex.getMessage());
			stockErrorPubliser.produceMsg(buidPedidoError(msg.getUuid(), msg.getPedidoId(), ex.getMessage()));
		}
        
        // publish for creditLimit
    }
   	
   	private PedidoStockError buidPedidoError(String uuid, Long pedidoId, String causa) {
   		PedidoStockError pedidoStockError =
   			PedidoStockError.builder()
   				.uuid(uuid)
   				.pedidoId(pedidoId)
   				.causa(causa)
   				.build();
   		return pedidoStockError;
   	}
}
