package com.rafael.product.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rafael.product.mongodb.domain.Product;
import com.rafael.product.mongodb.domain.State;

public interface ProductRepository extends MongoRepository<Product, Long> {
	List<Product> findByName(String name);
	List<Product> findByState(State state);
	
	List<Product> findByIdAndState(Long id, State state);

	List<Product> findByIdAndName(long id, String name);
}
