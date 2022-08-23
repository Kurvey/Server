package com.kurvey.u_life_kurly.product.service;

import com.kurvey.u_life_kurly.error.CustomException;
import com.kurvey.u_life_kurly.product.dto.ProductDto;
import com.kurvey.u_life_kurly.product.dto.ProductsByCategoryDto;
import com.kurvey.u_life_kurly.product.entity.Category;
import com.kurvey.u_life_kurly.product.entity.Product;
import com.kurvey.u_life_kurly.product.entity.Purchase;
import com.kurvey.u_life_kurly.product.entity.PurchasedProduct;
import com.kurvey.u_life_kurly.product.repository.CategoryRepository;
import com.kurvey.u_life_kurly.product.repository.ProductRepository;
import com.kurvey.u_life_kurly.product.repository.PurchaseRepository;
import com.kurvey.u_life_kurly.product.repository.PurchasedProductRepository;
import com.kurvey.u_life_kurly.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.kurvey.u_life_kurly.response.StatusCode.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchasedProductRepository purchasedProductRepository;

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
                        .map(ProductDto::new)
                        .collect(Collectors.toList())))
                .sorted(Comparator.comparing(pbc -> pbc.getProducts().size(), Comparator.reverseOrder()))
                .collect(Collectors.toList());

        return productsByCategoryDtoList;
    }

    @Transactional
    public void buyProduct(User user, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND));

        Purchase purchase = Purchase.builder()
                .user(user)
                .build();
        purchaseRepository.save(purchase);

        PurchasedProduct purchasedProduct = PurchasedProduct.builder()
                .purchase(purchase)
                .product(product)
                .quantity(1)
                .build();
        purchasedProductRepository.save(purchasedProduct);
    }

    @Transactional(readOnly = true)
    public Product findProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND));
    }
}

