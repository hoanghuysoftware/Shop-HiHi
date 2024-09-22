package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeProductRepo extends JpaRepository<TypeProduct, Long> {
}
