package com.salesmanager.SalesManager.services;



import com.salesmanager.SalesManager.exception.CategoryAlreadyExistException;
import com.salesmanager.SalesManager.models.CategoryModel;
import com.salesmanager.SalesManager.repositorys.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CategoryServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {

    }

    @Test
    public  void testCreateCategorySuccess()
    {
        CategoryModel category = CategoryModel.builder().idCategory(1L).nameCategory("   LActEos   ").build();
        when(categoryRepository.save(any(CategoryModel.class))).thenReturn(category);

        CategoryModel categoryCreated = categoryService.createCategory(category);

        verify(categoryRepository).save(any(CategoryModel.class));
        //prueba de la normalizacion del name category
        Assertions.assertEquals("lacteos", categoryCreated.getNameCategory());
    }
    @Test
    public void testCreateCategoryThrowsExceptionWhenExists()
    {
        CategoryModel categoryModel = CategoryModel.builder().nameCategory("lacteos").build();

        when(categoryRepository.countCategoryByName(any(String.class))).thenReturn((byte) 1);

        Assertions.assertThrows(
                CategoryAlreadyExistException.class,
                ()-> categoryService.createCategory(categoryModel),
                "The category already exists"
                );
    }
}
