package com.salesmanager.SalesManager.services;

import com.salesmanager.SalesManager.dto.CustomerDto;
import com.salesmanager.SalesManager.exception.CustomerNotFoundException;
import com.salesmanager.SalesManager.models.CustomerModel;
import com.salesmanager.SalesManager.repositorys.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public void deleteCustomerById(Long id)
    {
        customerRepository.deleteById(id);
    }

    @Transactional
    public CustomerModel updateCustomerById(Long id, CustomerDto customerDto) throws  CustomerNotFoundException
    {
        CustomerModel customerDB = customerRepository.findById(id).orElseThrow(
                ()-> new CustomerNotFoundException("Customer not found with ID: " + id)
        );

        customerDB.setName(normalize(customerDto.getName()));
        customerDB.setSurname(normalize(customerDto.getSurname()));
        customerDB.setTel(normalize(customerDto.getPhone()));

        customerRepository.save(customerDB);
        return customerDB;
    }

    @Transactional(readOnly = true)
    public CustomerModel getCustomerById(Long id) throws CustomerNotFoundException
    {
        return customerRepository.findById(id).orElseThrow(
                ()-> new CustomerNotFoundException("Customer not found with ID: " + id)
        );
    }

    @Transactional
    public CustomerDto createCustomer(CustomerDto customer)
    {
        customer.setName(normalize(customer.getName()));
        customer.setSurname(normalize(customer.getSurname()));

        CustomerModel customerDB = new CustomerModel(
                customer.getName(),
                customer.getSurname(),
                customer.getPhone()
        );
        customerRepository.save(customerDB);
        return customer;
    }



    private String normalize(String txt){
        return txt.trim().toLowerCase();
    }
}
