package com.rafael.product.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rafael.product.dtos.ProductDto;

@RequestMapping("/product")
public interface ProductAPIQueryController {

	@GetMapping
	ResponseEntity<List<ProductDto>> obtenerPedidos();
	
	@GetMapping("/{id}")
	ResponseEntity<ProductDto> obtenerPedidosById(
			@PathVariable(name = "id") Long id);
}
