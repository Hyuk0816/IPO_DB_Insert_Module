package com.example.ipodbinsert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class IpoDbInsertApplication {


    public static void main(String[] args) {
      SpringApplication.run(IpoDbInsertApplication.class, args);

    }


}
