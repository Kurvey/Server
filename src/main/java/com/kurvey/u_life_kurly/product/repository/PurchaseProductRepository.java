package com.kurvey.u_life_kurly.product.repository;

import com.kurvey.u_life_kurly.product.entity.PurchasedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseProductRepository extends JpaRepository<PurchasedProduct, Long> {
    PurchasedProduct findByPurchaseAndId(long purchase, long id);
}
