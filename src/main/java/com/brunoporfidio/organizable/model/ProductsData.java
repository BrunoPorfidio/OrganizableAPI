package com.brunoporfidio.organizable.model;

import java.io.Serializable;
import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class ProductsData implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String customerName;
    private String customerPhone;
    private String model;
    private String problem;
    private String inputDate;
    private String outputDate;
    
    @NotNull
    @Size(min=50,max=2000)
    private String description;
    
    @NotNull
    @Column(name = "productImage", columnDefinition="LONGTEXT")
    private String productImage;
    
}
