package com.project.ecommerce.repository;

import com.project.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE (p.category.name=:category OR :category='')" +
            "AND ((:min_price IS NULL AND :max_price IS NULL) OR (p.discounted_price BETWEEN :min_price AND :max_price))" +
            "AND (:min_discount IS NULL OR p.discount_percent >= :min_discount)" +
            "ORDER BY CASE WHEN :sort='price_low' THEN p.discounted_price END ASC," +
            "CASE WHEN :sort='price_high' THEN p.discounted_price END DESC")
    public List<Product> filterProduct(
            @Param("category") String category,
            @Param("min_price") Integer min_price,
            @Param("max_price") Integer max_price,
            @Param("min_discount") Integer min_discount,
            @Param("sort") String sort);
}
