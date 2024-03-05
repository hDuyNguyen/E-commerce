package com.project.ecommerce.repository;

import com.project.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Category findByName(String name);

    @Query("select c from Category c where c.name=:name and c.parentCategory.name=:parent_category_name")
    public Category findByNameAndParent(
            @Param("name") String name,
            @Param("parent_category_name") String parent_category_name);
}
