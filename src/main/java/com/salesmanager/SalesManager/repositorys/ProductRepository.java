package com.salesmanager.SalesManager.repositorys;

import com.salesmanager.SalesManager.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    @Query(value = "SELECT * FROM product WHERE category_id = :cod", nativeQuery = true)
    List<ProductModel> findByCategory(@Param("cod") Long cod);
}
