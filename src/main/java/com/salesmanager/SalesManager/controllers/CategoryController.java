package com.salesmanager.SalesManager.controllers;

import com.salesmanager.SalesManager.models.CategoryModel;
import com.salesmanager.SalesManager.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryModel> createCategory(@Valid @RequestBody CategoryModel cat){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        categoryService.createCategory(cat)
                );
    }
    @GetMapping
    public ResponseEntity<List<String>> allCategory()
    {
        return  ResponseEntity
                .status(HttpStatus.OK).
                body(categoryService.allCategory()
            );
    }

}
