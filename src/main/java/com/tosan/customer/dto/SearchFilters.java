package com.tosan.customer.dto;

import java.util.Date;

import com.tosan.customer.model.CustomerType;
import com.tosan.customer.model.Status;

import lombok.Data;

@Data
public class SearchFilters {
    String name;
    String family;
    String nin;
    Date birthDate;
    CustomerType type;
    Status state;
}
