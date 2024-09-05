package com.salesmanager.SalesManager.controllers;

import com.salesmanager.SalesManager.dto.CustomerDto;
import com.salesmanager.SalesManager.models.CustomerModel;
import com.salesmanager.SalesManager.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customer)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.createCustomer(customer));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<CustomerModel> getCustomerById(@PathVariable Long id)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.getCustomerById(id)
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerModel> updateCustomerByID(@PathVariable Long id, @RequestBody CustomerDto customer)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.updateCustomerById(id, customer)
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id)
    {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }
}
