package com.kurvey.u_life_kurly.product.repository;

import com.kurvey.u_life_kurly.product.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    Purchase findByUserId(Long userId);
}
