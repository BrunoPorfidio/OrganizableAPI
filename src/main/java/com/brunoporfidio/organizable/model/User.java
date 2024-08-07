package com.brunoporfidio.organizable.model;

import jakarta.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NamedQuery(name = "User.findByEmail", query = "select u from User u where u.email=:email")
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @Column(name = "companyName")
    private String companyName;
    
    @NotNull
    @Column(name = "userName")
    private String userName;
    
    @NotNull
    @Column(name = "name")
    private String name;
    
    @NotNull
    @Column(name = "lastname")
    private String lastname;
    
    @NotNull
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
}
