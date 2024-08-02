package com.brunoporfidio.organizable.dto;

import lombok.Data;


@Data
public class ProductsDataDTO {
    
    private Long id;
    private String customerName;
    private String customerPhone;
    private String model;
    private String problem;
    private String inputDate;
    private String outputDate;
    private String description;
    private String productImage;
    
}
