package com.brunoporfidio.organizable.repository;

import com.brunoporfidio.organizable.model.ProductsData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDataRepository extends CrudRepository<ProductsData, Long>{
    ProductsData findFirstByOrderById();
}
