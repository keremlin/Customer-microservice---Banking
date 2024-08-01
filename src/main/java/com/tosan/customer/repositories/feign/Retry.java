package com.tosan.customer.repositories.feign;

import feign.RetryableException;
import feign.Retryer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class Retry implements Retryer {
    private int counter = 1;
    @Value("${com.tosan.feign.maxRetry}")
    private int max ;

    @Override
    public void continueOrPropagate(RetryableException e) {
        if (++counter > max) throw e;
        log.info("Retry the feign : "+counter);
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw e;
        }
    }

    @Override
    public Retryer clone() {
        return new Retry();
    }
}