package com.project.mdyshop.repository;

import com.project.mdyshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String category);

    @Query("select c from Category c where c.name = :name and  c.parentCategory = :parent")
    Category findByNameAndParentCategory(@Param(("name")) String name, @Param("parent") String parent);

    @Query("select c from Category c where c.level = 0")
    List<Category> findParentCategory();

    @Query("select c from Category c where c.parentCategory.id = :categoryId")
    List<Category> findSubCategory(@Param("categoryId")Long categoryId);
}
