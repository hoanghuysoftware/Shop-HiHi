package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
    List<Image> getImagesByProductId(long productId);
}
