package com.tosan.customer.model;

public enum CustomerType {
    REAL,
    LEGAL;

    public static int toInteger(CustomerType item) {
        if (item == null)
            return -1;
        else if (item == CustomerType.REAL)
            return 0;
        else
            return 1;
    }
}
