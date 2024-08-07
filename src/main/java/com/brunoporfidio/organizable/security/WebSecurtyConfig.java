package com.brunoporfidio.organizable.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurtyConfig {
    
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((authorize) -> 
                        authorize.anyRequest().authenticated()
                );
        http
                .formLogin(withDefaults());
        http.
                httpBasic(withDefaults());
        return http.build();
    }
}