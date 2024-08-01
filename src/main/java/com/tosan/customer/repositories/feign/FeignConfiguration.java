package com.tosan.customer.repositories.feign;

import com.tosan.customer.dto.DepositDto;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FeignConfiguration {
    @Bean
    public ErrorDecoder getDecoder(){return  new LogErrorDecoder();}
    @Bean
    public Retryer getRetry(){return new Retry();}

}
