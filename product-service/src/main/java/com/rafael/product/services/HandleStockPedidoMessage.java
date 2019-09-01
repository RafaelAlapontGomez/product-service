package com.rafael.product.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.rafael.product.rabbitmq.messages.ItemMessage;
import com.rafael.product.services.exceptions.InsuficienteStokException;
import com.rafael.product.services.exceptions.ProductNotExistException;

public interface HandleStockPedidoMessage {
	
	static final String TOTAL = "total";
	static final String STOCK = "stock";

	@SuppressWarnings("rawtypes")
	HashMap<String, Optional> validarStock(List<ItemMessage> items) throws ProductNotExistException, InsuficienteStokException;
}
