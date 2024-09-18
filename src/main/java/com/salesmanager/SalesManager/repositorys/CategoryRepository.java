package com.salesmanager.SalesManager.repositorys;

import com.salesmanager.SalesManager.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository  extends JpaRepository<CategoryModel, Long> {
    //*
    @Query(value = "SELECT COUNT(name_category) AS count FROM category WHERE name_category = :nameCategory", nativeQuery = true)
    Byte countCategoryByName(@Param("nameCategory") String nameCategory);
    //
    @Query(value = "SELECT name_category FROM category", nativeQuery = true)
    List<String> findAllNameCategory();
    //
    Optional<CategoryModel> findByNameCategory(String name);
    //
    @Query(value = "SELECT id_category FROM `category` WHERE  name_category = :category", nativeQuery = true)
    Long findCodByNameCategory(@Param("category") String category);

}
