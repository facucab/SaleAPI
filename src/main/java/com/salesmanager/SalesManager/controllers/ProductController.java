package com.salesmanager.SalesManager.controllers;

import com.salesmanager.SalesManager.dto.ProductDto;
import com.salesmanager.SalesManager.dto.UpdateProductDto;
import com.salesmanager.SalesManager.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController
{
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto pro)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.createProduct(pro)
                );
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProduct()
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.allProduct());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductByCod(@PathVariable Long id)
    {
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(productService.getProductByCod(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteByCod(@PathVariable Long id)
    {
        productService.deleteProductByCod(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateByCod(@RequestBody UpdateProductDto p, @PathVariable Long id)
    {
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.updateProductByCod(p, id));
    }

    @GetMapping("/filter")
    public List<ProductDto> getProductsByCategory(@RequestParam String category)
    {
        return  productService.getProductsByCategory(category);
    }
}
