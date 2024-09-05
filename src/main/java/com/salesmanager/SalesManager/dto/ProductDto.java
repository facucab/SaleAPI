package com.salesmanager.SalesManager.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ProductDto {

    @NotNull(message = "The product code must not be null") //Global Handler
    @JsonAlias("productCode")
    @JsonProperty("productCode")
    private Long CodPro;

    @NotBlank(message = "the name must not be null")
    @Size(min = 3, max = 35, message = "name must be between 3 and 35 characters long")
    private String name;

    @Size(max = 150, message = "The description exceeds the 150-character limit")
    private String description;

    @Min(value = 0, message = "Stock cannot be negative")
    private int stock;

    @NotBlank(message = "The category cannot be null")
    @Size(max = 35, message = "The category exceeds the 35-character limit")
    private String category;



}
