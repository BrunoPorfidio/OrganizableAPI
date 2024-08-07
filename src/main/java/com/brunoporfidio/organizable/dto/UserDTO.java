package com.brunoporfidio.organizable.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String companyName;
    private String name;
    private String userName;
    private String lastname;
    private String email;
    private String password;
}
