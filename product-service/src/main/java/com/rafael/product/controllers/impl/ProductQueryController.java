package com.rafael.product.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rafael.product.controllers.ProductAPIQueryController;
import com.rafael.product.dtos.ProductDto;
import com.rafael.product.services.exceptions.NoDataFoundException;
import com.rafael.product.services.impl.ProductQueryServiceImpl;

@RestController
public class ProductQueryController implements ProductAPIQueryController {

	@Autowired
	ProductQueryServiceImpl productService;
	
	@Override
	public ResponseEntity<List<ProductDto>> obtenerPedidos() {
		return ResponseEntity.ok(productService.findAll());
	}

	@Override
	public ResponseEntity<ProductDto> obtenerPedidosById(Long id) {
		ProductDto productDto = null;
		try {
			productDto = productService.findById(id);
		} catch (NoDataFoundException ex) {
	         throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
	    }
		return ResponseEntity.ok(productDto);	}

}
