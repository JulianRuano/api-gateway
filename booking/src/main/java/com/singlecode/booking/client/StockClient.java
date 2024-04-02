package com.singlecode.booking.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("stock-microservice")
public interface StockClient {

    @RequestMapping("/stock/{code}")
    boolean strockAvailability(@PathVariable String code) ;  
    
}
