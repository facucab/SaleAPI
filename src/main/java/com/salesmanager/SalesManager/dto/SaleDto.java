package com.salesmanager.SalesManager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SaleDto {

    private String description;

    private Double price;

    private Long idCustomer;

    private Map<Long,Integer> products;
}

