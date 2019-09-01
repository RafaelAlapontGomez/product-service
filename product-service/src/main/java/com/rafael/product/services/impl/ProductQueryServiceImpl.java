package com.rafael.product.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.product.dtos.ProductDto;
import com.rafael.product.mongodb.domain.Product;
import com.rafael.product.mongodb.domain.State;
import com.rafael.product.mongodb.repository.ProductRepository;
import com.rafael.product.services.ProductQueryService;
import com.rafael.product.services.exceptions.NoDataFoundException;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Override
	public List<ProductDto> findAll() {
		List<Product> products = productRepository.findAll();
		return mapToProductDto(products);
	}

	@Override
	public ProductDto findById(Long id) throws NoDataFoundException {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new NoDataFoundException(String.format("Product %d not found", id)));
		return mapper.map(product, ProductDto.class);
	}

	@Override
	public List<ProductDto> findByName(String name) {
		List<Product> products = productRepository.findByName(name);
		return mapToProductDto(products);
	}
	
	@Override
	public List<ProductDto> findByState(State estado) {
		List<Product> products = productRepository.findByState(estado);
		return mapToProductDto(products);
	}
	
	@Override
	public ProductDto findByIdAndState(Long id, State state) {
		List<Product> products = productRepository.findByIdAndState(id, state);
		if (products.size() != 1) {
			return null;
		}
		return mapper.map(products.get(0), ProductDto.class);
	}

	private List<ProductDto> mapToProductDto(List<Product> products) {
		List<ProductDto> productsDto = new ArrayList<>();
		products.forEach((item -> productsDto.add(mapper.map(item, ProductDto.class))));
		return productsDto;
	}

}
