package com.vmoscalciuc.providedServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProvidedServices {

    public static void main(String[] args) {
        SpringApplication.run(ProvidedServices.class, args);
    }

}

