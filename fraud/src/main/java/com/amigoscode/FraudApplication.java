package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class FraudApplication {
    public static void main(String[] args) {
        SpringApplication.run(FraudApplication.class,args);
    }

}