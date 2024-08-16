package com.brunoporfidio.organizable.security.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLogin {

    private String emailUser;
    private String userName;
    
    @NotBlank
    private String password;
}
