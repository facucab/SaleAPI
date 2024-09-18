package com.salesmanager.SalesManager.services;

import com.salesmanager.SalesManager.models.CustomerModel;
import com.salesmanager.SalesManager.repositorys.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void TestDeleteCustomerById()
    {
        CustomerModel customer = CustomerModel.builder().NumCustomer(1L).build();
        doNothing().when(customerRepository).deleteById(customer.getNumCustomer());

        customerService.deleteCustomerById(customer.getNumCustomer());

        verify(customerRepository).deleteById(customer.getNumCustomer());
    }

    @Test
    public void TestGetCustomerByIdWhenCustomerExists()
    {
        CustomerModel customer = CustomerModel.builder().NumCustomer(100L)
                .name("customer").build();
        when(customerRepository.findById(customer.getNumCustomer())).thenReturn(Optional.of(customer));

        CustomerModel customerById = customerService.getCustomerById(customer.getNumCustomer());
        Assertions.assertNotNull(customerById);
        Assertions.assertEquals("customer", customerById.getName());
    }
}