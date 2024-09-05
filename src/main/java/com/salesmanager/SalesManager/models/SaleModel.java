package com.salesmanager.SalesManager.models;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name =  "sale")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SaleModel {
    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private Long idSale;

    @Column(length = 200)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private LocalDate saleDate;

    @ManyToOne
            (
                    cascade = CascadeType.MERGE
            )
    @JoinColumn(
            name = "customer_id"
    )
    private CustomerModel customer;
    @ManyToMany
            (
                    cascade = CascadeType.MERGE
            )
    @JoinTable(
                name = "sale_product",
                    joinColumns = @JoinColumn(
                            name = "sale_id",
                            referencedColumnName = "idSale"
                    ),
                    inverseJoinColumns = @JoinColumn(
                            name = "product_id",
                            referencedColumnName = "CodPro"
                    )
            )
    List<ProductModel> listPro;

    public SaleModel(String description, Double price, LocalDate saleDate, List<ProductModel> listPro) {
        this.description = description;
        this.price = price;
        this.saleDate = saleDate;
        this.listPro = listPro;
    }

    public SaleModel(String description, Double price, LocalDate saleDate, CustomerModel customer, List<ProductModel> listPro) {
        this.description = description;
        this.price = price;
        this.saleDate = saleDate;
        this.customer = customer;
        this.listPro = listPro;
    }
}
