package com.tosan.customer.repositories.feign;

import com.tosan.customer.dto.DepositDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "DepositResource", url = "http://"+"${com.tosan.deposit}"+"/api/deposit/")
public interface FeignDepositResources {

    @RequestMapping(method = RequestMethod.GET, value = "{nin}/getAllDeposit", consumes = "application/json")
    public List<DepositDto> getAllDepositsByNin(@PathVariable("nin") String nin);

    @RequestMapping(method = RequestMethod.GET, value = "{nin}/hasNinAnyDeposit",consumes = "application/json")
    public Boolean hasCustomerAnyDepositByNin(@PathVariable("nin") String nin);
}
