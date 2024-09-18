package com.salesmanager.SalesManager.repositorys;

import com.salesmanager.SalesManager.dto.ProductDto;
import com.salesmanager.SalesManager.models.CategoryModel;
import com.salesmanager.SalesManager.models.ProductModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @BeforeEach
    void setUp() {
        CategoryModel cat = CategoryModel.builder().nameCategory("lacteos").build();
        testEntityManager.persistAndFlush(cat);

        ProductModel pro = ProductModel.builder()
                .CodPro(100L)
                .stock(10)
                .category(cat)
                .name("leche")
                .description("leche 1L")
                .build();
        testEntityManager.persistAndFlush(pro);
    }

    @Test
    public void testFindByCategory() {
        List<ProductModel> listPro = productRepository.findByCategory(1L);

        //Tamaño lista
        Assertions.assertEquals(1, listPro.size());

        //
        Assertions.assertEquals(100L, listPro.getFirst().getCodPro());
        Assertions.assertEquals("leche", listPro.getFirst().getName());
        Assertions.assertEquals("leche 1L", listPro.getFirst().getDescription());
        Assertions.assertEquals(10, listPro.getFirst().getStock());
        Assertions.assertEquals("lacteos", listPro.getFirst().getCategory().getNameCategory());
    }
}