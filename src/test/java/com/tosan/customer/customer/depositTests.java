package com.tosan.customer.customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tosan.customer.model.Customer;
import com.tosan.customer.model.CustomerType;
import com.tosan.customer.repositories.CustomerRepository;
import com.tosan.customer.repositories.DepositResources;
import com.tosan.customer.repositories.DepositResourcesImpl;
import com.tosan.customer.services.CustomerServiceImpl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.tosan.customer.Exceptions.CustomerHasDepositException;
import com.tosan.customer.Exceptions.NinIsExistsException;
import com.tosan.customer.Exceptions.NinNotFoundException;
import com.tosan.customer.dto.DepositDto;
import com.tosan.customer.dto.SearchFilters;
import com.tosan.customer.model.*;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class depositTests {

    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository repo;

    @Mock
    DepositResourcesImpl deppsitResources;

    @Mock
    RestTemplate restTemplate;

    Customer customer;
    Customer newCustomer;
    List<Customer> listCustomer = new ArrayList<>();
    List<DepositDto> listDeposit=new ArrayList();

    // @BeforeAll 
    @BeforeEach 
    void beforeAll() {
        // customerService=new CustomerServiceImpl();
        // MokitoAnnotations.
        customer = new Customer(
                1,
                "3234563434",
                "Arash",
                "Yeg",
                "sdfsdf",
                "09142342354",
                null,
                Status.ENABLED,
                CustomerType.REAL,
                null);
        newCustomer = new Customer(
                1,
                "0324569845",
                "Adam",
                "Janbaz",
                "0Xfgk494",
                "09142342354",
                null,
                Status.ENABLED,
                CustomerType.REAL,
                null);
                listCustomer.clear();
                listCustomer.add(customer);
        listCustomer.add(new Customer(
                1,
                "3234563567",
                "Ali",
                "Moradian",
                "0x98909",
                "09122342354",
                null,
                Status.ENABLED,
                CustomerType.REAL,
                null));
        listCustomer.add(new Customer(
                1,
                "32345635454",
                "Mina",
                "Kahvand",
                "0x6969",
                "09182342354",
                null,
                Status.ENABLED,
                CustomerType.REAL,
                null));
listDeposit.clear();
        listDeposit.add(new DepositDto(
            1,
            "3234563434",
            894894,
            1,
            new Date(),
            new Date()
        ));
        listDeposit.add(new DepositDto(
            2,
            "3234563434",
            894895,
            0,
            new Date(),
            new Date()
        ));
        

        when(repo.findByNin("3234563434")).thenReturn(Optional.of(customer));
        when(repo.findByNin("3234563567")).thenReturn(Optional.of(listCustomer.get(1)));
        when(repo.findByNin("9234563430")).thenReturn(Optional.empty());
        when(deppsitResources.getAllDepositsByNin("3234563434")).thenReturn(listDeposit);
        when(deppsitResources.hasCustomerAnyDepositByNin("3234563434")).thenReturn(true);
        when(deppsitResources.hasCustomerAnyDepositByNin("9234563430")).thenReturn(false);
        when(deppsitResources.hasCustomerAnyDepositByNin("3234563567")).thenReturn(false);
        Mockito.doNothing().when(repo).delete(customer);
    }
    @Test
     void checkListAllDepositByNin(){
        assertEquals(2,customerService.getDepositList(customer).size());
    }
    @Test
     void checkListAllDepositByNinNotFound(){
        customer.setNin("9234563430");
        assertThrows(NinNotFoundException.class,()-> customerService.getDepositList(customer));
    }
   
    @Test
    void ckeckDeleteCustomerNotFoundException() {
        customer.setNin("9234563430");
        assertThrows(NinNotFoundException.class, () -> customerService.deleteCustomer(customer));
    }
    @Test
    void checkDeleteCustomerCustomerHasDepositYet(){
        assertThrows(CustomerHasDepositException.class,()->customerService.deleteCustomer(customer));
    }
    @Test
    void checkCustomerDelete(){
        assertEquals(listCustomer.get(1),customerService.deleteCustomer(listCustomer.get(1)));
    }

}
