package com.kurvey.u_life_kurly.product.service;

import com.kurvey.u_life_kurly.product.dto.ProductDto;
import com.kurvey.u_life_kurly.product.dto.ProductsByCategoryDto;
import com.kurvey.u_life_kurly.product.entity.Category;
import com.kurvey.u_life_kurly.product.entity.Product;
import com.kurvey.u_life_kurly.product.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    @Transactional
    public void saveProduct(ProductDto productDto) {
        Product product = productDto.toEntity();
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<ProductsByCategoryDto> searchProducts(String keyword) {
        List<ProductsByCategoryDto> productsByCategoryDtoList = new ArrayList<>();
        List<Product> products = productRepository.findAllByNameContaining(keyword);

        categoryRepository.findByNameContaining(keyword)
                .ifPresent(category -> products.addAll(productRepository.findAllByCategoryAndNameNotContaining(category, keyword)));

        if(products.isEmpty()) return productsByCategoryDtoList;

        Map<Category, List<Product>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        productsByCategoryDtoList = productsByCategory.entrySet().stream()
                .map(e -> new ProductsByCategoryDto(e.getKey().getId(), e.getKey().getName(), e.getValue().stream()
                        .map(this::convertEntityToDto)
                        .collect(Collectors.toList())))
                .sorted(Comparator.comparing(pbc -> pbc.getProducts().size(), Comparator.reverseOrder()))
                .collect(Collectors.toList());

        return productsByCategoryDtoList;
    }

    private ProductDto convertEntityToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .deliveryType(product.getDeliveryType())
                .productName(product.getName())
                .cost(product.getCost())
                .description(product.getDescription())
                .build();
    }
}

