package com.salesmanager.SalesManager.controllers;
import com.salesmanager.SalesManager.dto.SaleDto;
import com.salesmanager.SalesManager.models.SaleModel;
import com.salesmanager.SalesManager.repositorys.SaleRepository;
import com.salesmanager.SalesManager.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;
    @Autowired
    private SaleRepository saleRepository;

    @PostMapping("/create")
    public SaleDto createSale(@RequestBody SaleDto sale)
    {
        return saleService.createSale(sale);
    }

    @GetMapping("/all")
    public ResponseEntity< List<SaleModel> > allSale()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        saleService.allSale()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleModel> getSaleById(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        saleService.getSaleById(id)
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleById(@PathVariable Long id)
    {
        saleService.deleteSaleById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public  List<SaleModel> filterSaleByDate(
            @RequestParam("initialDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
    {
        return  saleService.filterSaleByDate(initialDate, endDate);
    }
}
