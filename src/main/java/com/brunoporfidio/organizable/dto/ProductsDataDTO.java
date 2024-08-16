package com.brunoporfidio.organizable.dto;

import jakarta.persistence.ElementCollection;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;


@Data
public class ProductsDataDTO {
    
    private Long id;
    @ElementCollection
    private Map<String, String> customAttributes = new HashMap<>();
   
    
}
