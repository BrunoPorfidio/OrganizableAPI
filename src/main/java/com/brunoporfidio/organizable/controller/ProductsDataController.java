package com.brunoporfidio.organizable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.brunoporfidio.organizable.dto.Message;
import com.brunoporfidio.organizable.dto.ProductsDataDTO;
import com.brunoporfidio.organizable.model.ProductsData;
import com.brunoporfidio.organizable.service.ProductsDataService;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/productsdata")
public class ProductsDataController {

    @Autowired
    private ProductsDataService productsDataService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductsData>> findAll() {
        List<ProductsData> productsData = productsDataService.findAll();
        return new ResponseEntity<>(productsData, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductsData>> findById(@PathVariable Long id) {
        Optional<ProductsData> productsDataDTO = productsDataService.findById(id);
        return ResponseEntity.ok(productsDataDTO);
    }

    @PostMapping("/addToUser/{userId}")
    public ResponseEntity<?> addProductToUser(
            @PathVariable Long userId,
            @RequestBody ProductsDataDTO productsDataDTO) {

        ProductsData productsData = new ProductsData();
        productsData.setCustomAttributes(productsDataDTO.getCustomAttributes());

        ProductsData createdProduct = productsDataService.addProductToUser(userId, productsData);

        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PostMapping("/addAttributes")
    public ResponseEntity<?> addAttributes(
            @RequestParam Long productId,
            @RequestParam Map<String, String> attributes) {

        try {
            productsDataService.addCustomAttribute(productId, attributes);
            return new ResponseEntity<>(new Message("Attributes added successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("Error adding attributes: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateAttributes/{id}")
    public ResponseEntity<?> updateAttributes(
            @PathVariable Long id,
            @RequestBody Map<String, String> attributes) {
        try {
            productsDataService.updateCustomAttribute(id, attributes);
            return new ResponseEntity<>(new Message("Attributes updated successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("Error updating attributes: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductsData(@PathVariable("id") Long id) {
        if (!productsDataService.existById(id)) {
            return new ResponseEntity<>(new Message("Product Data No Exist"), HttpStatus.BAD_REQUEST);
        }

        productsDataService.deleteProductsData(id);

        return new ResponseEntity<>(new Message("Product Data Deleted"), HttpStatus.OK);
    }

}
