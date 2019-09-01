package com.rafael.product.services;

public interface CounterService {
	
	static final String COUNTER_ID = "product";
	
	Long nextValue();
	Long beforeValue();
	Long currentValue();
}
