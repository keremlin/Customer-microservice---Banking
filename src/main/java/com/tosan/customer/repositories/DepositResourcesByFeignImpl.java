package com.tosan.customer.repositories;

import com.tosan.customer.dto.DepositDto;
import com.tosan.customer.repositories.feign.FeignDepositResources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Profile("feign")
@Service
public class DepositResourcesByFeignImpl implements DepositResources {
    private FeignDepositResources resources;

    @Autowired
    private void setFeignResources(FeignDepositResources client) {
        resources = client;
    }

    @Override
    public List<DepositDto> getAllDepositsByNin(String nin) {
        log.info("Before call the service");
        return resources.getAllDepositsByNin(nin);
    }

    @Override
    public Boolean hasCustomerAnyDepositByNin(String nin) {
        return resources.hasCustomerAnyDepositByNin(nin);
    }
}
