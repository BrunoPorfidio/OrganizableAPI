package com.brunoporfidio.organizable.repository;

import com.brunoporfidio.organizable.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <User, Long>{
    
    Optional<User> findByEmail(String email);
}
