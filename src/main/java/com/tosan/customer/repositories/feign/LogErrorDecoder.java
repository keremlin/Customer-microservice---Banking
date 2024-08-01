package com.tosan.customer.repositories.feign;

import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogErrorDecoder implements ErrorDecoder {
    private int counter = 1;

    @Override
    public Exception decode(String methodKey, Response response) {
        FeignException exception = FeignException.errorStatus(methodKey, response);
        log.info("decoder : "+counter);
        if (counter < 3 && (response.status() == 400 || response.status() == 403 || response.status() == 500)) {
            counter++;
            return new RetryableException(response.status(), "exception on feign",
                    response.request().httpMethod(), (Throwable) exception, (Long) null, response.request());
        } else {
            counter = 1;
            return new Exception("error on calling processor application");
        }
    }

}