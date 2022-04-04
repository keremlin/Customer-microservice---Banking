package com.tosan.customer.model;

public enum Status {

    ENABLED,
    DISABLED;

    public static int toInteger(Status item) {
        if (item == null)
            return -1;
        else if (item == Status.ENABLED)
            return 0;
        else
            return 1;
    }
}
