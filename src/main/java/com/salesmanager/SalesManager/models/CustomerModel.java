package com.salesmanager.SalesManager.models;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@Entity
@Table(name = "customer")
public class CustomerModel {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long NumCustomer;

    @Column(length = 35, nullable = false)
    private String name;

    @Column(length = 35, nullable = false)
    private String surname;

    @JsonProperty("phone")
    @Column(name = "phone")
    private String tel;


    public CustomerModel(String name, String surname, String tel) {
        this.name = name;
        this.surname = surname;
        this.tel = tel;
    }
}
