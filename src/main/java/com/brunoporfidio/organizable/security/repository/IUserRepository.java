package com.brunoporfidio.organizable.security.repository;

import com.brunoporfidio.organizable.security.model.UserS;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<UserS, Integer>{
    
    Optional <UserS> findByUserName(String userName);
    
    boolean existsByUserName(String userName);
    
    boolean existsByEmail(String email);

}
