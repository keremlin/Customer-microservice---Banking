package com.tosan.customer.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
    @NotBlank(message = "nin is mandatory")
    @Pattern(regexp="^([0-9]){10}$",message = "Nin must have 10 digits only")
    @Size(min = 10,max = 10)
    String nin;

    @NotBlank
    @Size(min=3)
    String name;

    @NotBlank
    @Size(min=3)
    String family;
    
    @NotBlank
    String code;

    @NotBlank
    @Size(max=10,min=10)
    String mobilePhone;

    Date birthDate;  
    Status status;
    CustomerType type;
    Date createdDate;
}
