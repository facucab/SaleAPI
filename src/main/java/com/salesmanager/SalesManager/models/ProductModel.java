package com.salesmanager.SalesManager.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@ToString
public class ProductModel {
    @Id
    private Long CodPro;

    @Column(name = "name", length = 35, nullable = false)
    private String name;

    @Column(name = "description", length = 150)
    private String description;

    @Column(name = "stock")
    private int stock;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "category_id"
    )
    private CategoryModel category;

    public ProductModel(Long codPro, String name, String description, int stock) {
        CodPro = codPro;
        this.name = name;
        this.description = description;
        this.stock = stock;
    }
}
