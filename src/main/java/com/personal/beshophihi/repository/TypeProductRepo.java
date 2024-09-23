package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeProductRepo extends JpaRepository<TypeProduct, Long> {
    @Query("select t from TypeProduct  t where t.isActive=true")
    List<TypeProduct> getTypeProductByActive();

    TypeProduct getTypeProductByName(String name);
}
