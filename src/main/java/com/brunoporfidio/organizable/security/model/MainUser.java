package com.brunoporfidio.organizable.security.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class MainUser implements UserDetails{

    private String userName;
    private String name;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    
    
    private MainUser( String userName, String name, String email, String password,  Collection<? extends GrantedAuthority> authorities) {
        this.name = userName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    
    public static  MainUser build(UserS user){
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getRolName().name())).collect(Collectors.toList());
        return new MainUser(user.getUserName(), user.getName(), user.getEmail(), user.getPassword(), authorities);
    }

        @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
       return userName;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
       
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
