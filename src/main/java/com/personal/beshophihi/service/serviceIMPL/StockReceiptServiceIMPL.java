package com.personal.beshophihi.service.serviceIMPL;

import com.personal.beshophihi.dto.ProductDTO;
import com.personal.beshophihi.dto.StockReceiptDTO;
import com.personal.beshophihi.dto.StockReceiptDetailDTO;
import com.personal.beshophihi.exception.EntityNotFound;
import com.personal.beshophihi.model.*;
import com.personal.beshophihi.repository.*;
import com.personal.beshophihi.service.StockReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockReceiptServiceIMPL implements StockReceiptService {
    private final StockReceiptRepo stockReceiptRepo;
    private final StockReceiptDetailRepo stockReceiptDetailRepo;
    private final ProductRepo productRepo;
    private final SupplierRepo supplierRepo;
    private final BrandRepo brandRepo;
    private final TypeProductRepo typeProductRepo;
    private final DiscountRepo discountRepo;
    private final ImageRepo imageRepo;

    @Override
    public List<StockReceipt> getAllStockReceipts() {
        return stockReceiptRepo.findAll();
    }

    @Override
    public StockReceipt getStockReceipt(Long id) {
        return stockReceiptRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found stock receipt with id: " + id)
        );
    }

    @Override
    public StockReceipt addStockReceipt(StockReceiptDTO stockReceiptDTO) { // add new product
        Supplier supplier = supplierRepo.findById(stockReceiptDTO.getIdSupplier()).orElseThrow(
                () -> new EntityNotFound("Not found supplier with id: " + stockReceiptDTO.getIdSupplier())
        );
        StockReceipt stockReceipt = StockReceipt.builder()
                .supplier(supplier)
                .receiptDate(new Date())
                .build();

        List<StockReceiptDetail> stockReceiptDetails = new ArrayList<>();
        for(StockReceiptDetailDTO stockDetailItem: stockReceiptDTO.getStockReceiptDetailDTOS()){
            StockReceiptDetail stockReceiptDetail = StockReceiptDetail.builder()
                    .product(createNewProduct(stockDetailItem.getProductDTO()))
                    .stockReceipt(stockReceipt)
                    .quantity(stockDetailItem.getQuantity())
                    .unitPrice(stockDetailItem.getUnitPrice())
                    .totalPrice(stockDetailItem.getTotalPrice())
                    .build();

            stockReceiptDetails.add(stockReceiptDetail);
        }
        stockReceipt.setStockReceiptDetails(stockReceiptDetails);
        return null;
    }

    private Product createNewProduct(ProductDTO productDTO){
        Brand brand = brandRepo.findById(productDTO.getBrandID()).orElseThrow(
                () -> new EntityNotFound("Not found brand with id: " + productDTO.getBrandID())
        );
        Discount discount = discountRepo.findById(productDTO.getDiscountID()).orElseThrow(
                () -> new EntityNotFound("Not found discount with id: " + productDTO.getDiscountID())
        );
        List<TypeProduct> typeProducts = new ArrayList<>();
        for(Long idType : productDTO.getTypeProductIDs()){
            TypeProduct typeProduct = typeProductRepo.findById(idType).orElseThrow(
                    () -> new EntityNotFound("Not found type product with id: " + idType)
            );
            typeProducts.add(typeProduct);
        }
        Product product = Product.builder()
                .CPU(productDTO.getCpu())
                .RAM(productDTO.getRam())
                .ROM(productDTO.getRom())
                .availableQuantity(productDTO.getAvailableQuantity())
                .card(productDTO.getCard())
                .description(productDTO.getDescription())
                .isActive(true)
                .name(productDTO.getName())
                .salePrice(productDTO.getSalePrice())
                .screen(productDTO.getScreen())
                .brand(brand)
                .discount(discount)
                .typeProducts(typeProducts)
                .build();

        List<Image> imageList = new ArrayList<>();
        for (MultipartFile file: productDTO.getFilesImages()){
            try {
                Image image = Image.builder()
                        .product(product)
                        .dataImage(file.getBytes())
                        .build();
                imageList.add(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        product.setImages(imageList);
        return productRepo.save(product);
    }

    @Override
    public StockReceipt updateStockReceipt(Long id, StockReceiptDTO stockReceiptDTO) {
        return null;
    }

    @Override
    public StockReceipt deleteStockReceipt(Long id) {
        return null;
    }
}
