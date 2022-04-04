package com.tosan.customer.repositories;

import java.util.List;
import java.util.Optional;

import com.tosan.customer.model.Customer;
import com.tosan.customer.model.CustomerType;
import com.tosan.customer.model.Status;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    public Optional<Customer> findByNin(String nin);
    public List<Customer> findAll();

    @Query("SELECT c FROM Customer c WHERE c.name LIKE ?1 AND c.family LIKE ?2 AND c.nin LIKE ?3 AND c.status = ?4 AND c.type = ?5 ")
    public List<Customer> findAllCustomers(String name,String family,String nin,Status state,CustomerType type);

}
