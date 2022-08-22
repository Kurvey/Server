package com.kurvey.u_life_kurly.product.service;

import com.kurvey.u_life_kurly.product.dto.ProductDto;
import com.kurvey.u_life_kurly.product.entity.Product;
import com.kurvey.u_life_kurly.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void saveProduct(ProductDto productDto) {
        Product product = productDto.toEntity();
        productRepository.save(product);
    }
}
