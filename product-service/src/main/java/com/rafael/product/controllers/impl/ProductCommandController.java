package com.rafael.product.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rafael.product.controllers.ProductAPICommandController;
import com.rafael.product.dtos.ProductDto;
import com.rafael.product.services.exceptions.NoDataFoundException;
import com.rafael.product.services.exceptions.ProductExistException;
import com.rafael.product.services.impl.ProductCommandServiceImpl;

@RestController
public class ProductCommandController implements ProductAPICommandController {

	@Autowired
	ProductCommandServiceImpl productService;
	
	@Override
	public ResponseEntity<ProductDto> createPedido(ProductDto productDto) {
		ProductDto newProductDto = null;
		try {
			newProductDto = productService.createProduct(productDto);
		} catch (ProductExistException ex) {
			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, ex.getMessage(), ex);
		}
		return ResponseEntity.ok(newProductDto);
	}

	@Override
	public ResponseEntity<ProductDto> updateCustomer(Long id, ProductDto productDto) {
		ProductDto newProductDto = null;
		try {
			newProductDto = productService.updateProduct(id, productDto);
		} catch (NoDataFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);		}
		return ResponseEntity.ok(newProductDto);
	}

	@Override
	public ResponseEntity<Void> deleteCustomer(Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.ok().build();
	}

}
