package com.tosan.customer.services;

import java.util.regex.Pattern;

import org.springframework.transaction.annotation.Transactional;

import com.tosan.customer.Exceptions.CustomerHasDepositException;
import com.tosan.customer.Exceptions.NinIsExistsException;
import com.tosan.customer.Exceptions.NinNotFoundException;
import com.tosan.customer.dto.DepositDto;
import com.tosan.customer.dto.SearchFilters;
import com.tosan.customer.model.Customer;
import com.tosan.customer.model.CustomerType;
import com.tosan.customer.model.Status;
import com.tosan.customer.repositories.CustomerRepository;
import com.tosan.customer.repositories.DepositResources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repo;

    @Autowired
    private DepositResources depositResource;

    @Transactional
    @Override
    public Customer create(Customer item) {
        if (validateNIN(item.getNin()) && isNinIdentical(item.getNin()))
            return repo.save(item);
        else
            throw new NinIsExistsException(item.getNin() + " is already exists");
    }

    @Override
    public List<Customer> getAllCustomerByFilter(SearchFilters filter) {
        SearchFilters changedFilter = changeToJPQL(filter);
        return repo.findAllCustomers(
                changedFilter.getName(),
                changedFilter.getFamily(),
                changedFilter.getNin(),
                changedFilter.getState(),
                changedFilter.getType());
    }

    @Override
    public List<DepositDto> getDepositList(Customer customer) {
        Customer foundedCustomer = repo.findByNin(customer.getNin())
                .orElseThrow(() -> new NinNotFoundException("Nin not Found"));
        return depositResource.getAllDepositsByNin(foundedCustomer.getNin());
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        Customer foundedCustomer = repo.findByNin(customer.getNin()).orElseThrow(() -> new NinNotFoundException());

        if (depositResource.hasCustomerAnyDepositByNin(foundedCustomer.getNin()) == false) {
            repo.delete(foundedCustomer);
        } else {
            throw new CustomerHasDepositException("The Customer has Deposit yet");
        }
        return foundedCustomer;
    }

    @Override
    @Transactional
    public Customer changeCustomerState(Customer customer) {
        Optional<Customer> optionalCustomer = repo.findByNin(customer.getNin());
        Customer changedCustomer = optionalCustomer.orElseThrow(() -> new NinNotFoundException("Nin not Found"));
        changedCustomer.setStatus(changedCustomer.getStatus() == Status.DISABLED ? Status.ENABLED : Status.DISABLED);
        changedCustomer = repo.save(changedCustomer);
        return changedCustomer;
    }

    @Override
    public boolean validateNIN(String nin) {
        if (nin == null)
            return false;
        Pattern pattern = Pattern.compile("^([0-9]){10}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nin);

        if (matcher.find()) {
            return true;
        }
        return false;
    }

    private boolean isNinIdentical(String nin) {
        Optional<Customer> temp = repo.findByNin(nin);
        if (temp.isEmpty())
            return true;
        return false;
    }

    private SearchFilters changeToJPQL(SearchFilters filter) {

        if (filter.getName() == null)
            filter.setName("%");
        else
            filter.setName(filter.getName() + "%");

        if (filter.getFamily() == null)
            filter.setFamily("%");
        else
            filter.setFamily(filter.getFamily() + "%");

        if (filter.getNin() == null)
            filter.setNin("%");
        else
            filter.setNin(filter.getNin() + "%");

        if (filter.getBirthDate() == null)
            filter.setBirthDate(null);
        if (filter.getState() == null)
            filter.setState(Status.ENABLED);
        if (filter.getType() == null)
            filter.setType(CustomerType.REAL);

        return filter;
    }

    @Override
    public Customer getCustomerByNin(String nin) {
        log.error("nin is accepted " + nin);
        return repo.findByNin(nin)
                .orElseThrow(() -> new NinNotFoundException("Nin not found or not valid"));
    }

    
}
