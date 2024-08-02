package com.brunoporfidio.organizable.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    private String companyName;
    private String name;
    private String lastname;
    private String email;
    private String password;
    
}
