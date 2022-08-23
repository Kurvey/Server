package com.kurvey.u_life_kurly.product.repository;

import com.kurvey.u_life_kurly.product.entity.Purchase;
import com.kurvey.u_life_kurly.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByUser(User user);
}
