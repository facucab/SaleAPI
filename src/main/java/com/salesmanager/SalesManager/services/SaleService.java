package com.salesmanager.SalesManager.services;

import com.salesmanager.SalesManager.dto.SaleDto;
import com.salesmanager.SalesManager.exception.CustomerNotFoundException;
import com.salesmanager.SalesManager.exception.OutOfStockException;
import com.salesmanager.SalesManager.exception.SaleNotFoundException;
import com.salesmanager.SalesManager.models.CustomerModel;
import com.salesmanager.SalesManager.models.ProductModel;
import com.salesmanager.SalesManager.models.SaleModel;
import com.salesmanager.SalesManager.repositorys.CustomerRepository;
import com.salesmanager.SalesManager.repositorys.ProductRepository;
import com.salesmanager.SalesManager.repositorys.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    private Logger LOGGER = LoggerFactory.getLogger(SaleService.class);

    @Transactional
    public SaleDto createSale(SaleDto sale)
    {
        CustomerModel customer = customerRepository.findById(sale.getIdCustomer()).orElseThrow(
                ()-> new CustomerNotFoundException("Customer not found")
        );

        //
        List<ProductModel> productModels = productRepository.findAllById(
                sale.getProducts().keySet()
        );

        if(productModels.isEmpty())
        {
            //tirar excepcion
            LOGGER.info("La lista esta vacias");
        }
        //
        productModels.forEach(
                (pro)->
                {
                    if(pro.getStock() < sale.getProducts().get(pro.getCodPro()))
                    {
                        throw new OutOfStockException(
                                "Product with ID " + pro.getCodPro() +  " (" + pro.getName() + ") is out of stock");
                    }
                    pro.setStock(
                            pro.getStock() - sale.getProducts().get(pro.getCodPro())
                    );
                }
        );
        //
        SaleModel saleDB  = new SaleModel(
                sale.getDescription(),
                sale.getPrice(),
                LocalDate.now(),
                customer,
                productModels
        );

        saleRepository.save(saleDB);
        return sale;
    }

    @Transactional(readOnly = true)
    public List<SaleModel> allSale()
    {
        return saleRepository.findAll();
    }

    public SaleModel getSaleById(Long id)
    {
        return saleRepository.findById(id)
                .orElseThrow(
                ()-> new SaleNotFoundException("Sale not found")
        );
    }

    @Transactional
    public void deleteSaleById(Long id)
    {
        saleRepository.deleteById(id);
    }
    @Transactional
    public List<SaleModel> filterSaleByDate(LocalDate initialDate, LocalDate endDate)
    {
        return saleRepository.filterSaleByDate(initialDate, endDate);
    }

}
