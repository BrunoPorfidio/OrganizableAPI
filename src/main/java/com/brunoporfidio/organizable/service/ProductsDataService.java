package com.brunoporfidio.organizable.service;

import com.brunoporfidio.organizable.model.ProductsData;
import com.brunoporfidio.organizable.repository.ProductDataRepository;
import com.brunoporfidio.organizable.security.model.UserS;
import com.brunoporfidio.organizable.security.repository.IUserRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductsDataService {
    
    @Autowired
    private ProductDataRepository productDataRepository;
    
    @Autowired
    private IUserRepository userRepository;

    public ProductsData addProductToUser(Long userId, ProductsData productData) {
        UserS user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        productData.setUser(user);
        ProductsData savedProduct = productDataRepository.save(productData);

        user.getProducts().add(savedProduct);
        userRepository.save(user);

        return savedProduct;
    }
    
    public ProductsData addCustomAttribute(Long productId, Map<String, String> attributes) {
        ProductsData productsData = productDataRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
             productsData.addCustomAttribute(entry.getKey(), entry.getValue());
        }

        return productDataRepository.save(productsData);
    }
    
    public ProductsData updateCustomAttribute(Long productId, Map<String, String> attributes) {
        ProductsData productsData = productDataRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
             productsData.addCustomAttribute(entry.getKey(), entry.getValue());
        }

        return productDataRepository.save(productsData);
    }
    
    public List<ProductsData> findAll(){
        return (List<ProductsData>) productDataRepository.findAll();
    }
    
    public Optional<ProductsData> findById(Long id){
        return productDataRepository.findById(id);
    }
    
    public boolean existById(Long id){
        return productDataRepository.existsById(id);
    }
    
    public void deleteProductsData(Long id){
        productDataRepository.deleteById(id);
    }
}
