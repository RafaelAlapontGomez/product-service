package com.rafael.product.config;

import java.util.ArrayList;
import java.util.List;

import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rafael.product.dtos.ProductDto;
import com.rafael.product.dtos.convertes.StateToStringConverter;
import com.rafael.product.dtos.convertes.StringToStateConverter;
import com.rafael.product.mongodb.domain.Product;
import com.rafael.product.mongodb.domain.State;

@Configuration
public class DozerConfig {
	
	@Bean(name = "org.dozer.Mapper")
    public DozerBeanMapper mapper() throws Exception {
      DozerBeanMapper mapper = new DozerBeanMapper();
      mapper.addMapping(objectMappingBuilder);
      mapper.setCustomConverters(getCustomConverters());
      return mapper;
    }
    
    BeanMappingBuilder objectMappingBuilder = new BeanMappingBuilder() {
        @Override
        protected void configure() {
        	mapping(String.class, State.class );
            mapping(Product.class, ProductDto.class)
                .fields("state", "state", FieldsMappingOptions.customConverter(StateToStringConverter.class) );
        }
    };
    
    private List<CustomConverter> getCustomConverters() {
    	List<CustomConverter> converters = new ArrayList<>();
    	StateToStringConverter stateToStringConverter = new StateToStringConverter();
    	StringToStateConverter stringToStateConverter = new StringToStateConverter();
    	converters.add(stateToStringConverter);
    	converters.add(stringToStateConverter);
    	return converters;
    }
}
