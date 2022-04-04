package com.tosan.customer.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.tosan.customer.dto.DepositDto;
import com.tosan.customer.dto.SearchFilters;
import com.tosan.customer.model.*;
import com.tosan.customer.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    // @Autowired
    // private DepositResources depositResource;

    @PostMapping("/create")
    public ResponseEntity<Customer> customer(@Valid @RequestBody final Customer customer) {
        return ResponseEntity.ok().body(service.create(customer));
    }

    @PostMapping("/getAllCustomer")
    public ResponseEntity<List<Customer>> getAllCustomer(@RequestBody SearchFilters filter) {
        return ResponseEntity.ok().body(
                service.getAllCustomerByFilter(filter));
    }

    @PostMapping("/changeState")
    public ResponseEntity<Customer> changeState(@RequestBody Customer customer) {
        return ResponseEntity.ok().body(
                service.changeCustomerState(customer));
    }

    @GetMapping("/{nin}/getAllDeposits")
    public ResponseEntity<List<DepositDto>> getAllDeposit(@PathVariable String nin) {
        return ResponseEntity.ok().body(
                service.getDepositList(new Customer(0, nin, "empty", "empty", "empty", "empty", new Date(),
                        Status.DISABLED, CustomerType.LEGAL, new Date())));
    }

    @GetMapping("/{nin}/deleteCustomer")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String nin) {
        return ResponseEntity.ok().body(
                service.deleteCustomer(new Customer(0, nin, "empty", "empty", "empty", "empty", new Date(),
                        Status.DISABLED, CustomerType.LEGAL, new Date())));
    }

    @GetMapping("/{nin}/getCustomerByNin")
    public ResponseEntity<Customer> getCustomerByNin(@PathVariable @NotBlank() String nin) {
        return ResponseEntity.ok().body(service.getCustomerByNin(nin));
    }

}
