package com.brunoporfidio.organizable.security.DTO;

import lombok.Data;

@Data
public class AuthResponseDTO {
    String token;
    String refreshToken;
}
