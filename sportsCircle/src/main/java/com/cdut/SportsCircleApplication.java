package com.cdut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SportsCircleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SportsCircleApplication.class, args);
    }

}
