package com.brunoporfidio.organizable.security.DTO;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class NewUser {
    private String userName;
    private String name;
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();
}
