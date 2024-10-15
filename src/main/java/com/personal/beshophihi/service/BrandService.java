package com.personal.beshophihi.service;

import com.personal.beshophihi.dto.BrandDTO;
import com.personal.beshophihi.model.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAllBrands();
    List<Brand> getAllBrandActive();
    Brand getBrandById(Long id);
    Brand createBrand(BrandDTO brandDTO);
    Brand updateBrand(Long id, BrandDTO brandDTO);
    void deleteBrand(Long id);
    Brand updateActive(Long id, Boolean active);
}
