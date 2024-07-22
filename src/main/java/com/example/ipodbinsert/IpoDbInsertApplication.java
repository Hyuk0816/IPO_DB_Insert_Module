package com.example.ipodbinsert;

import com.amazonaws.Request;
import com.amazonaws.handlers.RequestHandler;
import com.amazonaws.handlers.RequestHandler2;
import com.amazonaws.util.TimingInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.naming.Context;

@SpringBootApplication
public class IpoDbInsertApplication extends RequestHandler2 {


    public static void main(String[] args) {
        SpringApplication.run(IpoDbInsertApplication.class, args);
    }


}
