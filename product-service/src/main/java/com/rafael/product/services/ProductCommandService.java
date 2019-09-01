package com.rafael.product.services;

import java.util.HashMap;

import com.rafael.product.dtos.ProductDto;
import com.rafael.product.services.exceptions.NoDataFoundException;
import com.rafael.product.services.exceptions.ProductExistException;

public interface ProductCommandService {
	ProductDto createProduct(ProductDto productDto) throws ProductExistException;
	void deleteProduct(Long id);
	ProductDto updateProduct(Long id, ProductDto productDto) throws NoDataFoundException;
	void updateStockPedido(HashMap<Long, Long> stockPedido);
}
