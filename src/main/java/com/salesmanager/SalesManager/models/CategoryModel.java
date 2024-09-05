package com.salesmanager.SalesManager.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long idCategory;

    @JsonProperty("category")
    @JsonAlias("category")
    @Column(name = "name_category", length = 35, nullable = false)
    @NotBlank(message = "the category must not be null")
    @Size(min= 3, max = 35, message = "Category name must be between 3 and 35 characters long")
    private String nameCategory;

    public CategoryModel(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
