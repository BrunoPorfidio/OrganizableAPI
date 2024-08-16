package com.brunoporfidio.organizable.security.model;

import com.brunoporfidio.organizable.security.enums.RolName;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RolName rolName;

    public Rol(){
        
    }
    
    public Rol(RolName  rolName){
        this.rolName = rolName;
    }
}
