package com.brunoporfidio.organizable.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.brunoporfidio.organizable.dto.Message;
import com.brunoporfidio.organizable.dto.ProductsDataDTO;
import com.brunoporfidio.organizable.model.ProductsData;
import com.brunoporfidio.organizable.service.ProductsDataService;
import org.apache.commons.validator.EmailValidator;

@RestController
@RequestMapping("/ProductsData")
public class ProductsDataController {
        
    @Autowired
    private ProductsDataService productsDataService;
    
    @GetMapping("/all")
    public ResponseEntity<List<ProductsData>> findAllUser(){
        List<ProductsData>  productsData = productsDataService.findAll();
        return new ResponseEntity<>(productsData, HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> createProductsData (@RequestBody ProductsDataDTO productsDataDTO){
     
        if ( StringUtils.isBlank( productsDataDTO.getCustomerName() ) )
            return new ResponseEntity<>( new Message("Customer Name Empty"), HttpStatus.BAD_REQUEST );
         
        if ( StringUtils.isBlank( productsDataDTO.getCustomerPhone() ) )
            return new ResponseEntity<>( new Message("Customer Name Empty"), HttpStatus.BAD_REQUEST );
         
        if (StringUtils.isBlank(productsDataDTO.getModel() ) )
            return new ResponseEntity<>(new Message("Model Empty"), HttpStatus.BAD_REQUEST);

        if ( StringUtils.isBlank( productsDataDTO.getProblem() ) )
            return new ResponseEntity<>( new Message("Problem Empty"), HttpStatus.BAD_REQUEST );
        
        if ( StringUtils.isBlank( productsDataDTO.getInputDate() ) )
            return new ResponseEntity<>( new Message("Input Date Empty"), HttpStatus.BAD_REQUEST );
        
        if ( StringUtils.isBlank( productsDataDTO.getOutputDate() ) )
            return new ResponseEntity<>( new Message("output Date Empty"), HttpStatus.BAD_REQUEST );
        
        if ( StringUtils.isBlank( productsDataDTO.getDescription() ) )
            return new ResponseEntity<>( new Message("Description Empty"), HttpStatus.BAD_REQUEST );
        
        if ( StringUtils.isBlank( productsDataDTO.getProductImage() ) )
            return new ResponseEntity<>( new Message("Product image Empty"), HttpStatus.BAD_REQUEST );
        
        ProductsData newProductsData = new ProductsData();
        newProductsData.setCustomerName(productsDataDTO.getCustomerName());
        newProductsData.setCustomerPhone(productsDataDTO.getCustomerPhone());
        newProductsData.setModel(productsDataDTO.getModel());
        newProductsData.setProblem(productsDataDTO.getProblem());
        newProductsData.setInputDate(productsDataDTO.getInputDate());
        newProductsData.setOutputDate(productsDataDTO.getOutputDate());
        newProductsData.setDescription(productsDataDTO.getDescription());
        newProductsData.setProductImage(productsDataDTO.getProductImage());
        
        productsDataService.createProductsData(newProductsData);
        
        return new ResponseEntity<>( new Message("Products Data Created"), HttpStatus.CREATED);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?>  editProductsData(@PathVariable("id") Long id, @RequestBody ProductsDataDTO productsDataDTO){
        if (!productsDataService.existById(id))
            return new ResponseEntity<>(new Message("No exist"), HttpStatus.NOT_FOUND);
        
        if ( StringUtils.isBlank( productsDataDTO.getCustomerName() ) )
            return new ResponseEntity<>( new Message("Customer Name Empty"), HttpStatus.BAD_REQUEST );
         
        if ( StringUtils.isBlank( productsDataDTO.getCustomerPhone() ) )
            return new ResponseEntity<>( new Message("Customer Name Empty"), HttpStatus.BAD_REQUEST );
         
        if (StringUtils.isBlank(productsDataDTO.getModel() ) )
            return new ResponseEntity<>(new Message("Model Empty"), HttpStatus.BAD_REQUEST);

        if ( StringUtils.isBlank( productsDataDTO.getProblem() ) )
            return new ResponseEntity<>( new Message("Problem Empty"), HttpStatus.BAD_REQUEST );
        
        if ( StringUtils.isBlank( productsDataDTO.getInputDate() ) )
            return new ResponseEntity<>( new Message("Input Date Empty"), HttpStatus.BAD_REQUEST );
        
        if ( StringUtils.isBlank( productsDataDTO.getOutputDate() ) )
            return new ResponseEntity<>( new Message("output Date Empty"), HttpStatus.BAD_REQUEST );
        
        if ( StringUtils.isBlank( productsDataDTO.getDescription() ) )
            return new ResponseEntity<>( new Message("Description Empty"), HttpStatus.BAD_REQUEST );
        
        if ( StringUtils.isBlank( productsDataDTO.getProductImage() ) )
            return new ResponseEntity<>( new Message("Product image Empty"), HttpStatus.BAD_REQUEST );
        
        ProductsData editProductsData =  productsDataService.findById(id).get();
        editProductsData.setCustomerName(productsDataDTO.getCustomerName());
        editProductsData.setCustomerPhone(productsDataDTO.getCustomerPhone());
        editProductsData.setModel(productsDataDTO.getModel());
        editProductsData.setProblem(productsDataDTO.getProblem());
        editProductsData.setInputDate(productsDataDTO.getInputDate());
        editProductsData.setOutputDate(productsDataDTO.getOutputDate());
        editProductsData.setDescription(productsDataDTO.getDescription());
        editProductsData.setProductImage(productsDataDTO.getProductImage());
        
        productsDataService.createProductsData(editProductsData);
        
        return new ResponseEntity<>( new Message("Produtcs Data Updated"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductsData( @PathVariable("id")Long id){
        if( !productsDataService.existById(id))
            return new ResponseEntity<>(new Message("Product Data No Exist"),  HttpStatus.BAD_REQUEST);
        
        productsDataService.deleteProductsData(id);
        
        return new ResponseEntity<>( new Message("Product Data Deleted"), HttpStatus.OK);
    }
    
}
