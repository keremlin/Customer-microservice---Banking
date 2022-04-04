package com.tosan.customer.customer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tosan.customer.model.CustomerType;
import com.tosan.customer.model.Status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
 class enumsTest {

    @Test
    @DisplayName("Check Enum integer values")
    void StatusEnumTest(){
        assertTrue(Status.toInteger(Status.ENABLED)==0);
        assertTrue(Status.toInteger(Status.DISABLED)==1);
    }

    @Test
    @DisplayName("Check CustomerType enum integer value")
    void CustomerEnumTest(){
        assertTrue(CustomerType.toInteger(CustomerType.REAL)==0);
        assertTrue(CustomerType.toInteger(CustomerType.LEGAL)==1);
    }
}
