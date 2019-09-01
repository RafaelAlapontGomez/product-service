package com.rafael.product.mapping;

import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rafael.product.dtos.ProductDto;
import com.rafael.product.mongodb.domain.Product;
import com.rafael.product.mongodb.domain.State;

@SpringBootTest
class ProductMappingTest {

	@Autowired
	DozerBeanMapper mapper;

    @DisplayName("Test Convert entity to Dto")
    @Test
    public void convertEntityToDto() {
		ProductDto productDto = mapper.map(buildProduct(), ProductDto.class);

    	Assertions.assertAll("Product Dto", 
    			() -> Assertions.assertEquals(new Long(1), productDto.getId()),
    			() -> Assertions.assertEquals("Calculadora", productDto.getName()),
    			() -> Assertions.assertEquals(new Double(50.0), productDto.getUnitCost()),
    			() -> Assertions.assertEquals(new Long(100), productDto.getStock()),
    			() -> Assertions.assertEquals("Inactivo", productDto.getState()));
}

	@DisplayName("Test Convert Dto to entity")
    @Test
    public void ConvertDtoToEntity() {
		Product product = mapper.map(buildProductDto(), Product.class);
    	
    	Assertions.assertAll("Product Entity", 
    			() -> Assertions.assertEquals(new Long(1), product.getId()),
    			() -> Assertions.assertEquals("Ordenador", product.getName()),
    			() -> Assertions.assertEquals(new Double(1000.0), product.getUnitCost()),
    			() -> Assertions.assertEquals(new Long(100), product.getStock()),
    			() -> Assertions.assertEquals(State.Activo, product.getState()));

    }

	private ProductDto buildProductDto() {
		ProductDto productDto = 
			ProductDto.builder()
				.id(1L)
				.name("Ordenador")
				.unitCost(1000.0)
				.stock(100L)
				.state("Activo")
				.build();
		return productDto;
	}

    private Product buildProduct() {
		Product product =
			Product.builder()
				.id(1L)
				.name("Calculadora")
				.unitCost(50.0)
				.stock(100L)
				.state(State.Inactivo)
				.build();
		return product;
	}
}
