package com.rafael.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rafael.product.dtos.ProductDto;

@RequestMapping("/product")
public interface ProductAPICommandController {
	
	@PostMapping
	ResponseEntity<ProductDto> createPedido(@RequestBody ProductDto productDto);
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> updateCustomer(
			@PathVariable(name = "id") Long id,
			@RequestBody ProductDto productDto);
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable(name = "id") Long id);	

}
