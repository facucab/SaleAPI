package com.salesmanager.SalesManager.repositorys;

import com.salesmanager.SalesManager.models.SaleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<SaleModel, Long> {
    @Query(value = "SELECT * FROM sale WHERE sale_date BETWEEN :initialDate AND  :endDate", nativeQuery = true)
    List<SaleModel> filterSaleByDate(@Param("initialDate")LocalDate initialDate , @Param("endDate") LocalDate endDate);
}
