package com.tosan.customer.repositories;

import java.util.List;

import com.tosan.customer.dto.DepositDto;

public interface DepositResources {
    
    public List<DepositDto> getAllDepositsByNin(String nin);
    public Boolean hasCustomerAnyDepositByNin(String nin);
    
}
