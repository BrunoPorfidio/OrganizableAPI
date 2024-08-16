package com.brunoporfidio.organizable.security;

import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurtyConfig {
    
    public SecurityFilterChain websecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorize -> authorize.anyRequest().authenticated())
            .formLogin(withDefaults())
            .httpBasic(withDefaults());
        return http.build();
    }
}