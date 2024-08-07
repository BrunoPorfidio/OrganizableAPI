package com.brunoporfidio.organizable.security.DTO;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLogin {

    @NotBlank
    private String EmailUser;
    
    @NotBlank
    private String password;
}
