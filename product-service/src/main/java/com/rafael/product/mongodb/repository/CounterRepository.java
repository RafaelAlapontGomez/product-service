package com.rafael.product.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rafael.product.mongodb.domain.Counter;

public interface CounterRepository extends MongoRepository<Counter, String> {

}
