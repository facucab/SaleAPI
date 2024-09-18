package com.salesmanager.SalesManager.repositorys;

import com.salesmanager.SalesManager.models.CategoryModel;
import com.salesmanager.SalesManager.models.CustomerModel;
import com.salesmanager.SalesManager.models.ProductModel;
import com.salesmanager.SalesManager.models.SaleModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class SaleRepositoryTest {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        //Create customer
        CustomerModel customer = CustomerModel.builder().name("Customer").surname("Test").tel("12345678").build();
        testEntityManager.persistAndFlush(customer);
        //Create category
        CategoryModel cat =  CategoryModel.builder().nameCategory("lacteos").build();
        testEntityManager.persistAndFlush(cat);
        // Create product
        ProductModel pro = ProductModel.builder().CodPro(100L).name("leche")
                .description("leche 1L").stock(10).category(cat).build();
        testEntityManager.persistAndFlush(pro);
        //create Sale_1
        testEntityManager.persistAndFlush(
                SaleModel.builder()
                        .description("Sale").price(20.3).saleDate(LocalDate.parse("2024-06-11"))
                        .customer(customer)
                        .listPro(List.of(pro))
                        .build()
        );
        //crate Sale_2
        testEntityManager.persistAndFlush(
                SaleModel.builder()
                        .description("Sale_2").price(30.3).saleDate(LocalDate.parse("2024-08-11"))
                        .customer(customer)
                        .listPro(List.of(pro))
                        .build()
        );
    }

    @Test
    public  void filterSaleByDateValid()
    {
        List<SaleModel> listSales = saleRepository.filterSaleByDate(LocalDate.parse("2024-06-11"), LocalDate.parse("2024-08-11"));
        Assertions.assertEquals(2, listSales.size());
    }

    @Test
    public  void filterSaleByDateNotValid()
    {
        List<SaleModel> listSales = saleRepository.filterSaleByDate(LocalDate.parse("2023-06-11"), LocalDate.parse("2023-08-11"));
        Assertions.assertEquals(0, listSales.size());
    }
}