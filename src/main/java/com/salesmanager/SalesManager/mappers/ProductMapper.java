package com.salesmanager.SalesManager.mappers;

import com.salesmanager.SalesManager.dto.ProductDto;
import com.salesmanager.SalesManager.models.ProductModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {
    /**
     *  ProductModel => ProductDto
     * @param proDto
     * @return
     */
    public ProductModel ConvertProductModel(ProductDto proDto)
    {
        return new ProductModel(
                proDto.getCodPro(),
                proDto.getName(),
                proDto.getDescription(),
                proDto.getStock()
        );
    }

    public ProductDto ConvertProductDto(ProductModel proModel)
    {
        return  new ProductDto(
                proModel.getCodPro(),
                proModel.getName(),
                proModel.getDescription(),
                proModel.getStock(),
                proModel.getCategory().getNameCategory()
        );
    }

    public List<ProductDto> ConvertListProductModel(List<ProductModel> listProModel)
    {
        List<ProductDto> listProDto = new ArrayList<>();
        listProModel.forEach(
                (p)-> {
                    listProDto.add(ConvertProductDto(p));
                }
        );
        return  listProDto;
    }


}
