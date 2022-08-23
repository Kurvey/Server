package com.kurvey.u_life_kurly.product.service;

import com.kurvey.u_life_kurly.product.dto.CategoryDto;
import com.kurvey.u_life_kurly.product.dto.ProductDto;
import com.kurvey.u_life_kurly.product.entity.Product;
import com.kurvey.u_life_kurly.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;



    @Transactional
    public void saveProduct(ProductDto productDto) {
        Product product = productDto.toEntity();
        productRepository.save(product);
    }

    @Transactional
    public List<CategoryDto> searchProducts(String keyword) {
        List<Product> products = productRepository.findAllByNameContaining(keyword);
        List<CategoryDto> categoryDtos = new ArrayList<>();

        if(products.isEmpty()) return categoryDtos;

        Map<String, List<Product>> productsByCategory = products.stream().collect(Collectors.groupingBy(p -> p.getCategory().getName()));
        categoryDtos = productsByCategory.entrySet().stream()
                .map(e -> new CategoryDto(e.getKey(), e.getValue().stream()
                        .map(this::convertEntityToDto)
                        .collect(Collectors.toList())))
                .sorted(Comparator.comparing(categoryDto -> categoryDto.getProducts().size(), Comparator.reverseOrder()))
                .collect(Collectors.toList());

        return categoryDtos;
    }



    private ProductDto convertEntityToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .deliveryType(product.getDeliveryType())
                .cost(product.getCost())
                .description(product.getDescription())
                .build();
    }



    }

