package com.rafael.product.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.product.mongodb.domain.Counter;
import com.rafael.product.mongodb.repository.CounterRepository;
import com.rafael.product.services.CounterService;

@Service
public class CounterServiceImpl implements CounterService {

	@Autowired
	CounterRepository counterRepository;
	
	@Override
	public Long nextValue() {
		Counter counter = counterRepository.findById(COUNTER_ID).get();
		Long currentId = counter.getQty();
		counter.setQty(currentId + 1);
		counterRepository.save(counter);
		return currentId;
	}

	@Override
	public Long beforeValue() {
		Counter counter = counterRepository.findById(COUNTER_ID).get();
		Long currentId = counter.getQty();
		counter.setQty(currentId - 1);
		counterRepository.save(counter);
		return currentId;
	}

	@Override
	public Long currentValue() {
		Counter counter = counterRepository.findById(COUNTER_ID).get();
		Long currentId = counter.getQty();
		return currentId;
	}

}
