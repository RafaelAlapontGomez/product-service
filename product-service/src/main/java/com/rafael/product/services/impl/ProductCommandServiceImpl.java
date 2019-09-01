package com.rafael.product.services.impl;

import java.util.HashMap;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.product.dtos.ProductDto;
import com.rafael.product.mongodb.domain.Product;
import com.rafael.product.mongodb.repository.ProductRepository;
import com.rafael.product.services.ProductCommandService;
import com.rafael.product.services.exceptions.NoDataFoundException;
import com.rafael.product.services.exceptions.ProductExistException;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Autowired
	CounterServiceImpl counterService;
	
	@Override
	public ProductDto createProduct(ProductDto productDto) throws ProductExistException {
		if ( productRepository.findByName(productDto.getName()).size() != 0) {
			throw new ProductExistException(String.format("Product %s exists", productDto.getName()));
		}
		
		Product product = mapper.map(productDto, Product.class);
		product.setId(counterService.nextValue());
		
		Product newProduct = productRepository.save(product);
		return mapper.map(newProduct, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(Long id, ProductDto productDto) throws NoDataFoundException {
		@SuppressWarnings("unused")
		Product oldProduct = productRepository.findById(id)
				.orElseThrow(() -> new NoDataFoundException(String.format("Product %d not found", id)));
		if (!id.equals(productDto.getId())) {
			throw new NoDataFoundException(String.format("Product %d not found", productDto.getId()));
		}
		
		Product newProduct = productRepository.save(mapper.map(productDto, Product.class));
		return mapper.map(newProduct, ProductDto.class);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public void updateStockPedido(HashMap<Long, Long> stockPedido) {
		stockPedido.forEach((key,qty)->{
			Product product = productRepository.findById(key).get();
			product.setStock(qty);
			productRepository.save(product);
		});		
	}
}
