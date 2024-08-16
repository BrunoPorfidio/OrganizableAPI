package com.brunoporfidio.organizable.security.DTO;

import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class JwtDTO {

    private String token;
    private String bearer = "Bearer";
    private String userName;
    private Collection<? extends GrantedAuthority> authorities;
    
    public JwtDTO(String token, String userName,  Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.userName = userName;
        this.authorities = authorities;
    }
}
