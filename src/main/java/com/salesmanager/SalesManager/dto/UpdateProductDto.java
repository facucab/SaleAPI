package com.salesmanager.SalesManager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UpdateProductDto {

    private String name;

    private String description;

    private Integer stock;

}
