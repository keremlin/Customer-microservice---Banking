package com.tosan.customer.repositories;

import java.util.List;

import com.tosan.customer.dto.DepositDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DepositResourcesImpl implements DepositResources {

    @Autowired
    private RestTemplate rest;

    @Value("${com.tosan.deposit}")
    private String depositURL;
    @Value("${com.tosan.customer}")
    private String customerURL;
    @Value("${com.tosan.transaction}")
    private String transactionURL;

    @Override
    public List<DepositDto> getAllDepositsByNin(String nin) {
        // return rest.getForObject("http://localhost:8090/api/deposit/" + nin + "/getAllDeposit", List.class);
        return rest.getForObject("http://" + depositURL + "/api/deposit/" + nin + "/getAllDeposit", List.class);
    }

    @Override
    public Boolean hasCustomerAnyDepositByNin(String nin) {
        // return rest.getForObject("http://localhost:8090/api/deposit/" + nin + "/hasNinAnyDeposit", Boolean.class);
        return rest.getForObject("http://" + depositURL + "/api/deposit/" + nin + "/hasNinAnyDeposit", Boolean.class);
    }

}
