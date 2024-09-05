package com.salesmanager.SalesManager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CustomerDto {

    @NotBlank
    @Size(min = 3, max = 35, message = "Name must be between 3 and 35 characters long")
    private String name;

    @NotBlank
    @Size(min = 3, max = 35, message = "Surname must be between 3 and 35 characters long")
    private String surname;

    @Size(min = 8, max = 8, message = "Phone number must be exactly 8 digits long")
    private String phone;
}
