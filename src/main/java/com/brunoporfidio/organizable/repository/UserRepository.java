package com.brunoporfidio.organizable.repository;

import com.brunoporfidio.organizable.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <User, Integer>{
    
   User findByEmail(@Param(("email")) String email);
   
   boolean existsByEmail(String email);

}
