package com.personal.beshophihi.service.serviceIMPL;

import com.personal.beshophihi.dto.BrandDTO;
import com.personal.beshophihi.exception.EntityNotFound;
import com.personal.beshophihi.exception.ExistsEntityException;
import com.personal.beshophihi.model.Brand;
import com.personal.beshophihi.repository.BrandRepo;
import com.personal.beshophihi.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceIMPL implements BrandService {
    private final BrandRepo brandRepo;


    @Override
    public List<Brand> getAllBrands() {
        return brandRepo.findAll();
    }

    @Override
    public List<Brand> getAllBrandActive() {
        return brandRepo.getBrandByActiveTrue();
    }

    @Override
    public Brand getBrandById(Long id) {
        return brandRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found brand by id: " + id)
        );
    }

    @Override
    public Brand createBrand(BrandDTO brandDTO) {
        Brand brand = brandRepo.getBrandByName(brandDTO.getBrandName());
        if (brand != null) {
            throw new ExistsEntityException("Brand already exists.");
        }
        return brandRepo.save(Brand
                .builder()
                .name(brandDTO.getBrandName())
                .isActive(true)
                .build());
    }

    @Override
    public Brand updateBrand(Long id, BrandDTO brandDTO) {
        Brand oldBrand = brandRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found brand by id: " + id));
        oldBrand.setName(brandDTO.getBrandName());
        oldBrand.setActive(true);
        return brandRepo.save(oldBrand);
    }

    @Override
    public void deleteBrand(Long id) {
        Brand brand = brandRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found brand by id: " + id));
        brand.setActive(false);
        brandRepo.save(brand);
    }

    @Override
    public Brand updateActive(Long id, Boolean active) {
        Brand brand = brandRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found brand by id: " + id));
        brand.setActive(active);
        return brandRepo.save(brand);
    }
}
