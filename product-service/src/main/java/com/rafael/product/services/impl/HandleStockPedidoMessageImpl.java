package com.rafael.product.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.product.dtos.ProductDto;
import com.rafael.product.mongodb.domain.State;
import com.rafael.product.rabbitmq.messages.ItemMessage;
import com.rafael.product.services.HandleStockPedidoMessage;
import com.rafael.product.services.exceptions.InsuficienteStokException;
import com.rafael.product.services.exceptions.ProductNotExistException;

@Service
public class HandleStockPedidoMessageImpl implements HandleStockPedidoMessage {

	@Autowired
	ProductQueryServiceImpl productService;
	
	@SuppressWarnings("rawtypes")
	@Override
	public HashMap<String, Optional> validarStock(List<ItemMessage> items) throws ProductNotExistException, InsuficienteStokException {
		HashMap<Long, Long> newStock = new HashMap<>();
		List<String> errors = new ArrayList<>();
		double total = 0.0;
		
		for(ItemMessage item: items) {
			ProductDto productDto = productService.findByIdAndState(item.getProductId(), State.Activo);
			if (productDto == null) {
				throw new ProductNotExistException(String.format("Product %d not exists", item.getProductId()));
			}
			if (productDto.getStock() - item.getQty() < 0) {
				errors.add(String.format("Insuficiente stock para %s", productDto.getName()));
			} else {
				if (newStock.containsKey(productDto.getId())) {
					Long newQty = newStock.get(productDto.getId()) - item.getQty();
					if (newQty > 0) {
						newStock.replace(productDto.getId(), 
						         newStock.get(productDto.getId()), 
						         newQty);
					} else {
						errors.add(String.format("Insuficiente stock para %s", productDto.getName()));
					}
				} else {
					newStock.put(productDto.getId(), productDto.getStock() - item.getQty());
				}
				total += item.getQty() * productDto.getUnitCost();
			}
		}
		
		if (errors.size() != 0) {
			StringBuilder strError = new StringBuilder("");
			errors.forEach((item) -> strError.append(item).append(";"));
			strError.deleteCharAt(strError.length() - 1);
			throw new InsuficienteStokException(strError.toString());
		}
		
		HashMap<String, Optional> valorDevuelto = new HashMap<>();
		valorDevuelto.put(TOTAL, Optional.of(total));
		valorDevuelto.put(STOCK, Optional.of(newStock));
		return valorDevuelto;

	}

}
