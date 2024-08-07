package com.brunoporfidio.organizable.security.service;

import com.brunoporfidio.organizable.security.enums.RolName;
import com.brunoporfidio.organizable.security.model.Rol;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.brunoporfidio.organizable.security.repository.RolRepository;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;
    
    public  Optional<Rol>getByRoleName(RolName rolName){
        return rolRepository.findByRolName(rolName);
    }
    
    public void save(Rol rol){
        rolRepository.save(rol);
    }
}
