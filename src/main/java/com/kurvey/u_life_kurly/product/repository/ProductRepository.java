package com.kurvey.u_life_kurly.product.repository;

import com.kurvey.u_life_kurly.product.entity.Category;
import com.kurvey.u_life_kurly.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByNameContaining(String name);
    List<Product> findAllByCategoryAndNameNotContaining(Category category, String name);
}
