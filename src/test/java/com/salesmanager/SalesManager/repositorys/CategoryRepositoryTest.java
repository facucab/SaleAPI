package com.salesmanager.SalesManager.repositorys;

import com.salesmanager.SalesManager.models.CategoryModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    TestEntityManager testEntityManager;

    //Se ejecuta antes del test
    @BeforeEach
    void setUp() {
        testEntityManager.persistAndFlush(CategoryModel.builder().nameCategory("lacteos").build());
        testEntityManager.persistAndFlush(CategoryModel.builder().nameCategory("almacen").build());
        testEntityManager.persistAndFlush(CategoryModel.builder().nameCategory("snacks").build());
        testEntityManager.persistAndFlush(CategoryModel.builder().nameCategory("refrescos").build());
    }

    @Test
    public void testFindById()
    {
        CategoryModel cat =  categoryRepository.findById(1L).orElseThrow();
        Assertions.assertEquals("lacteos", cat.getNameCategory());
    }
    @Test
    public void testCountCategoryByNameExists()
    {
        byte countNameCategory = categoryRepository.countCategoryByName("lacteos");
        Assertions.assertEquals(1, countNameCategory);
    }
    @Test
    public void testCountCategoryByNameNotExists()
    {
        byte countNameCategory = categoryRepository.countCategoryByName("congelados");
        Assertions.assertEquals(0, countNameCategory);
    }

    @Test
    public void testFindAllNameCategory()
    {
        List<String> listCategories = categoryRepository.findAllNameCategory();

        //cantidad de elementos
        Assertions.assertEquals(4, listCategories.size());
        //Que incluya los elementos
        List<String> listExpected = Arrays.asList(
                "lacteos",
                "almacen",
                "snacks",
                "refrescos"
        );
        Assertions.assertEquals(listExpected, listCategories);
    }

    @Test
    public void testFindCodByNameCategoryExists()
    {
        Long cod = categoryRepository.findCodByNameCategory("lacteos");
        Assertions.assertEquals(1, cod);
    }

    @Test
    public  void testFindCodByNameCategoryNotExists()
    {
        Long cod = categoryRepository.findCodByNameCategory("no lacteos");
        Assertions.assertNull(cod);
    }
}