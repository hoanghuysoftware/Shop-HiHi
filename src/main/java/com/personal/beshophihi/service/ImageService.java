package com.personal.beshophihi.service;

import com.personal.beshophihi.dto.ImageDTO;
import com.personal.beshophihi.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<Image> getImagesByIdProduct(Long id);
    List<Image> createImagesByIdProduct(Long id, List<MultipartFile> files);
}
