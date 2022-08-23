package com.kurvey.u_life_kurly.product.repository;

import com.kurvey.u_life_kurly.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNameContaining(String name);
}
