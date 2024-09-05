package com.salesmanager.SalesManager.repositorys;

import com.salesmanager.SalesManager.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

}
