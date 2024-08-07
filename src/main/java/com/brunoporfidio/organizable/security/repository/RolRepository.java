package com.brunoporfidio.organizable.security.repository;

import com.brunoporfidio.organizable.security.enums.RolName;
import com.brunoporfidio.organizable.security.model.Rol;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository<Rol, Integer> {
    Optional<Rol>findByRolName(RolName rolName);
}
