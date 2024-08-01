package com.tosan.customer.dto;

import java.util.Date;

public record DepositDto(
        int id,
        String nin,
        int depositNumber,
        int Balance,
        Date startDate,
        Date endDate) { }
