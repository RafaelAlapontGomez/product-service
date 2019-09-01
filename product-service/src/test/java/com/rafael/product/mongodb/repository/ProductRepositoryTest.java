package com.rafael.product.mongodb.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rafael.product.mongodb.domain.Product;
import com.rafael.product.mongodb.domain.State;

@SpringBootTest
class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepository;
	
    @DisplayName("Test Save product")
    @Disabled
	@Test
	void createProdutTest() {
		Product productNew = productRepository.save(buildProduct());
		
    	Assertions.assertAll("Product Entity", 
    			() -> Assertions.assertEquals(new Long(1), productNew.getId()),
    			() -> Assertions.assertEquals("Calculadora", productNew.getName()),
    			() -> Assertions.assertEquals(new Double(50.0), productNew.getUnitCost()),
    			() -> Assertions.assertEquals(new Long(100), productNew.getStock()),
    			() -> Assertions.assertEquals(State.Inactivo, productNew.getState()));
	}
    
//    @DisplayName("Test Obtener product by id and state")
//    @ParameterizedTest(name = "{index} => a={0}, b={1}")
//    @CsvSource({
//        "1, Activo"
//        "2, Activo",
//        "3, Activo",
//        "4, Inactivo"
//    })
//    void obtenerProductoActivo(Long productId, String state) {
//    	State estado = State.getStateEnun(state);
//		List<Product> products = productRepository.findByIdAndState(productId, estado);
//    	Assertions.assertNotNull(products);
//    	Assertions.assertEquals(1, products.size());
//    }

    @DisplayName("Test Obtener product by id")
    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3})
    void findByIdTest(Long id) {
		Product product = productRepository.findById(id).get();
    	Assertions.assertNotNull(product);
    }
    
    @DisplayName("Test Obtener product by name")
    @ParameterizedTest
    @ValueSource(strings = {"Calculadora", "iPhone", "Galaxy 20"})
    void findByNameTest(String name) {
		List<Product> products = productRepository.findByName(name);
    	Assertions.assertNotNull(products);
    	Assertions.assertEquals(1, products.size());
    }
    

    @DisplayName("Test Obtener product by id and name")
    @ParameterizedTest(name = "{index} => a={0}, b={1}")
    @CsvSource({
        "1, Calculadora",
        "2, iPhone",
        "3, Galaxy 20",
    })
    void findByIdAndNameTest(Long id, String name) {
		List<Product> product = productRepository.findByIdAndName(id, name);
    	Assertions.assertNotNull(product);
    	Assertions.assertEquals(1, product.size());
    }
    
    @DisplayName("Test Obtener product by id and name")
    @ParameterizedTest(name = "{index} => a={0}, b={1}")
    @CsvSource({
        "1, Activo",
        "2, Activo",
        "3, Activo",
    })
    void findByIdAndStateTest(Long id, String state) {
    	State estado = State.getStateEnun(state);
		List<Product> product = productRepository.findByIdAndState(id, estado);
    	Assertions.assertNotNull(product);
    	Assertions.assertEquals(1, product.size());
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
