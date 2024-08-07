package com.brunoporfidio.organizable.security.DTO;

import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class JwtDTO {

    private String token;
    private String bearer = "Bearer";
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
}
