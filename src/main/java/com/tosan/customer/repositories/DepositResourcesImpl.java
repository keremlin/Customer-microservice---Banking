package com.tosan.customer.repositories;

import java.util.List;

import com.tosan.customer.dto.DepositDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DepositResourcesImpl implements DepositResources {

    @Autowired
    private RestTemplate rest;

    @Override
    public List<DepositDto> getAllDepositsByNin(String nin) {
        return rest.getForObject("http://localhost:8090/api/deposit/" + nin + "/getAllDeposit", List.class);
    }

    @Override
    public Boolean hasCustomerAnyDepositByNin(String nin) {
        return rest.getForObject("http://localhost:8090/api/deposit/" + nin + "/hasNinAnyDeposit", Boolean.class);
    }

}
