package com.tosan.customer.customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


import com.tosan.customer.model.Customer;
import com.tosan.customer.model.CustomerType;
import com.tosan.customer.repositories.CustomerRepository;
import com.tosan.customer.repositories.DepositResourcesImpl;
import com.tosan.customer.services.CustomerServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tosan.customer.Exceptions.NinIsExistsException;
import com.tosan.customer.Exceptions.NinNotFoundException;
import com.tosan.customer.dto.SearchFilters;
import com.tosan.customer.model.*;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
 class CustomerApplicationTests {

	@InjectMocks
	CustomerServiceImpl customerService;
	
	@Mock
	CustomerRepository repo;

	@Mock 
	DepositResourcesImpl deppsitResources;
	
	Customer customer;
	Customer newCustomer;
	List<Customer> listCustomer=new ArrayList<>();
	
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
		when(repo.findAllCustomers("%", "%", "%", Status.ENABLED,
				CustomerType.REAL)).thenReturn(listCustomer);
		when(repo.findByNin("3234563434")).thenReturn(Optional.of(customer));
		when(repo.save(newCustomer)).thenReturn(newCustomer);
	}
	
	
	void beforeEach() {

		
	}
	@Test
	void getAllCustomers(){
		assertTrue(customerService.getAllCustomerByFilter(new SearchFilters())!=null);
		assertTrue(customerService.getAllCustomerByFilter(new SearchFilters()).size()>0);
	}
	@Test
	void createCustomer(){
		//check if the repeated nin is founded
		assertThrows(NinIsExistsException.class,()->customerService.create(customer));
		//check if new nin is added
		assertEquals("0324569845",customerService.create(newCustomer).getNin());
		//check if new nin has validation problems
		newCustomer.setNin("03245698");
		assertThrows(NinIsExistsException.class,()->customerService.create(newCustomer));
	}
	@Test
	void validateNin(){
		assertTrue(customerService.validateNIN("0923451232"));
		assertTrue(customerService.validateNIN("0023244567"));
		assertTrue(customerService.validateNIN("0232445676"));
		assertFalse(customerService.validateNIN(""));
		assertFalse(customerService.validateNIN(null));
		assertFalse(customerService.validateNIN("23244565"));
		assertFalse(customerService.validateNIN("asdfdsfded"));
		assertFalse(customerService.validateNIN("۱۱۲۳۴۲۳۲۴۵"));
	}
	@Test
	void customerDepositList(){
		assertTrue(true);
	}
	
	@Test
	void changeCustomerState() {
		Customer testCase = new Customer(
				1,
				"3234563567",
				"Ali",
				"Moradian",
				"0x98909",
				"09122342354",
				null,
				Status.ENABLED,
				CustomerType.REAL,
				null);
		when(repo.findByNin("3234563567")).thenReturn(Optional.of(testCase));
		when(repo.save(any(Customer.class))).thenAnswer(returnsFirstArg());
		Customer testedCustomer = customerService.changeCustomerState(testCase);
		assertTrue(testedCustomer != null);
		assertTrue(testCase.getStatus()== testedCustomer.getStatus());
		assertEquals("3234563567",testedCustomer.getNin());
		testCase.setNin("4445673355");
		assertThrows(NinNotFoundException.class,()->customerService.changeCustomerState(testCase));
	}
	@Test
	void checkGetCustomerByNin(){
		String nin="3234563434";
		assertNotNull(customerService.getCustomerByNin(nin));
		assertEquals(nin,customerService.getCustomerByNin(nin).getNin());
	}
}
