package com.brunoporfidio.organizable.repository;

import com.brunoporfidio.organizable.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <User, Integer>{
    
   User findByEmail(@Param(("email")) String email);
//   Optional<User> findByEmail(@Param(("email"))  String email);


}
