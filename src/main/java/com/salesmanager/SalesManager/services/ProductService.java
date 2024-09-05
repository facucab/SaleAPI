package com.salesmanager.SalesManager.services;

import com.salesmanager.SalesManager.dto.ProductDto;
import com.salesmanager.SalesManager.dto.UpdateProductDto;
import com.salesmanager.SalesManager.exception.CategoryNotFoundException;
import com.salesmanager.SalesManager.exception.ProductAlreadyExistsException;
import com.salesmanager.SalesManager.exception.ProductNotFoundException;
import com.salesmanager.SalesManager.mappers.ProductMapper;
import com.salesmanager.SalesManager.models.CategoryModel;
import com.salesmanager.SalesManager.models.ProductModel;
import com.salesmanager.SalesManager.repositorys.CategoryRepository;
import com.salesmanager.SalesManager.repositorys.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductDto> getProductsByCategory(String category) throws CategoryNotFoundException
    {
        Long cod = categoryRepository.findCodByNameCategory(normalize(category));
        if(cod == null)
        {
            throw  new CategoryNotFoundException("Category Not Found");
        }
        List<ProductModel> proModel = productRepository.findByCategory(cod);
        return productMapper.ConvertListProductModel(proModel);
    }

    @Transactional
    public ProductDto updateProductByCod(UpdateProductDto updateProductDto, Long cod) throws ProductNotFoundException
    {
        ProductModel proModel = productRepository.findById(cod).orElseThrow(
                ()-> new ProductNotFoundException("Product with code " + cod + " not found")
        );

        proModel.setName( updateProductDto.getName() == null ? proModel.getName() : updateProductDto.getName() );
        proModel.setDescription( updateProductDto.getDescription() == null ? proModel.getDescription() : updateProductDto.getDescription());
        proModel.setStock( updateProductDto.getStock() == null ? proModel.getStock() : updateProductDto.getStock());

        productRepository.save(proModel);
        return productMapper.ConvertProductDto(proModel);
    }

    @Transactional
    public void deleteProductByCod(Long cod) throws ProductNotFoundException
    {
        if(!productRepository.existsById(cod))
        {
            throw new ProductNotFoundException("Product NotFound");
        }
        productRepository.deleteById(cod);
    }

    @Transactional(readOnly = true)
    public ProductDto getProductByCod(Long cod)
    {
        ProductModel proModel = productRepository.findById(cod).orElseThrow(
                ()-> new ProductNotFoundException("Product with code " + cod + " not found")
        );
        return productMapper.ConvertProductDto(proModel);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> allProduct()
    {
        List<ProductModel> proModel = productRepository.findAll();
        return  productMapper.ConvertListProductModel(proModel);
    }

    @Transactional
    public ProductDto createProduct(ProductDto pro) throws CategoryNotFoundException, ProductAlreadyExistsException
    {
        pro.setName(normalize(pro.getName()));
        pro.setCategory(normalize(pro.getCategory()));

        CategoryModel cat = categoryRepository.findByNameCategory(pro.getCategory()).orElseThrow(
                ()-> new CategoryNotFoundException("Category Not Found")
        );

        if(productRepository.existsById(pro.getCodPro()))
        {
            throw new ProductAlreadyExistsException("Product with code " + pro.getCodPro() + " already exists");
        }

        ProductModel proModel = productMapper.ConvertProductModel(pro);
        proModel.setCategory(cat);
        productRepository.save(proModel);
        return pro;
    }

    private String normalize(String txt)
    {
        txt = txt.trim().toLowerCase();
        return txt;
    }
}
