
package com.brunoporfidio.organizable.repository;

import com.brunoporfidio.organizable.model.UserRol;
import org.springframework.data.repository.CrudRepository;

public interface UserRolRepository extends CrudRepository<UserRol, Long> {
    void deleteByUserId(Integer userId);
}
