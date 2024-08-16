package com.brunoporfidio.organizable.model;

import com.brunoporfidio.organizable.security.model.UserS;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
@Entity
@Table(name = "products_data")
public class ProductsData{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @Column(name = "custom_attributes", columnDefinition = "JSON")
    private String customAttributesJson;

    @ElementCollection
    @MapKeyColumn(name = "attribute_name")
    @Column(name = "attribute_value")
    @CollectionTable(name = "products_data_custom_attributes", joinColumns = @JoinColumn(name = "product_id"))
    private Map<String, String> customAttributes = new HashMap<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserS user;
    
    public void addCustomAttribute(String key, String value) {
        this.customAttributes.put(key, value);
    }
    
}
