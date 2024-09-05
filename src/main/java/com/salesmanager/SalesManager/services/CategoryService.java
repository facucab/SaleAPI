package com.salesmanager.SalesManager.services;
import com.salesmanager.SalesManager.exception.CategoryAlreadyExistException;
import com.salesmanager.SalesManager.models.CategoryModel;
import com.salesmanager.SalesManager.repositorys.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<String> allCategory()
    {
        return categoryRepository.findAllNameCategory();
    }

    @Transactional
    public CategoryModel createCategory(CategoryModel cat) throws CategoryAlreadyExistException
    {
        cat.setNameCategory( normalizeNameCategory(cat.getNameCategory()));
        if(categoryRepository.countCategoryByName(cat.getNameCategory()) > 0)
        {
            throw  new CategoryAlreadyExistException("The category already exists");
        }
        categoryRepository.save(cat);
        return cat;
    }


    private String normalizeNameCategory(String txt)
    {
       txt =  txt.trim();
       txt = txt.toLowerCase();
       return txt;
    }
}
