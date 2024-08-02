package com.brunoporfidio.organizable.service;

import com.brunoporfidio.organizable.model.ProductsData;
import com.brunoporfidio.organizable.repository.ProductDataRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductsDataService {
    
    private final ProductDataRepository productDataRepository;
    
    @Autowired
    public ProductsDataService( ProductDataRepository productDataRepository){
        this.productDataRepository = productDataRepository;
    }
    
    public ProductsData createProductsData(ProductsData productsData){
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
    
    public ProductsData editProductsData(ProductsData productsData){
        return productDataRepository.save(productsData);
    }
    
    public void deleteProductsData(Long id){
        productDataRepository.deleteById(id);
    }
}
