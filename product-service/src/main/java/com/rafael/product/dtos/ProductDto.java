package com.rafael.product.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductDto implements Serializable {
	@NotNull
    private Long id;
	
    @NotNull
    private String name;

    @NotNull
    private Double unitCost;
    
    @NotNull
    private Long stock;
    
    @NotNull
    private String state;

}
