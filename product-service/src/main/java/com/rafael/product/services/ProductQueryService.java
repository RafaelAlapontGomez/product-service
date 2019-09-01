package com.rafael.product.services;

import java.util.List;

import com.rafael.product.dtos.ProductDto;
import com.rafael.product.mongodb.domain.State;
import com.rafael.product.services.exceptions.NoDataFoundException;

public interface ProductQueryService {

	List<ProductDto> findAll();
	ProductDto findById(Long id) throws NoDataFoundException;
	List<ProductDto> findByName(String name);
	List<ProductDto> findByState(State estado);
	ProductDto findByIdAndState(Long id, State state);
}
