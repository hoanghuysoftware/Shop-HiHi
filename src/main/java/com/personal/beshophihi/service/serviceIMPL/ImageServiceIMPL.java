package com.personal.beshophihi.service.serviceIMPL;

import com.personal.beshophihi.dto.ImageDTO;
import com.personal.beshophihi.exception.EntityNotFound;
import com.personal.beshophihi.model.Image;
import com.personal.beshophihi.model.Product;
import com.personal.beshophihi.repository.ImageRepo;
import com.personal.beshophihi.repository.ProductRepo;
import com.personal.beshophihi.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceIMPL implements ImageService {
    private final ImageRepo imageRepo;
    private final ProductRepo productRepo;

    @Override
    public List<Image> getImagesByIdProduct(Long id) {
        return imageRepo.getImagesByProductId(id);
    }

    @Override
    public List<Image> createImagesByIdProduct(Long id, List<MultipartFile> images) {
        Product product = productRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found product with id: " + id)
        );
        List<Image> imageList = new ArrayList<>();
        boolean update = false;
        for(MultipartFile file : images){
            try {
                if(!update){
                    product.setThumbnail(file.getBytes());
                    product.setActive(true);
                    update = true;
                }
                Image image = Image.builder()
                        .product(product)
                        .dataImage(file.getBytes())
                        .build();
                imageRepo.save(image);
                imageList.add(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return imageList;
    }
}
