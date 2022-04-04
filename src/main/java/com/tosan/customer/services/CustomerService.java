package com.tosan.customer.services;

import com.tosan.customer.dto.DepositDto;
import com.tosan.customer.dto.SearchFilters;
import com.tosan.customer.model.Customer;

import java.util.List;

public interface CustomerService {
    public abstract Customer create(Customer item);

    public abstract boolean validateNIN(String nin);

    public abstract List<Customer> getAllCustomerByFilter(SearchFilters filter);

    public abstract List<DepositDto> getDepositList(Customer customer);

    public abstract Customer deleteCustomer(Customer customer);

    public abstract Customer changeCustomerState(Customer customer);

    public abstract Customer getCustomerByNin(String nin);
}
