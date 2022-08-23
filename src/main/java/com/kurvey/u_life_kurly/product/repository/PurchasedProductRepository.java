package com.kurvey.u_life_kurly.product.repository;

import com.kurvey.u_life_kurly.product.entity.Purchase;
import com.kurvey.u_life_kurly.product.entity.PurchasedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchasedProductRepository extends JpaRepository<PurchasedProduct, Long> {
    List<PurchasedProduct> findAllByPurchaseIsInAndProductCategoryId(List<Purchase> purchases, Long categoriId);
}
