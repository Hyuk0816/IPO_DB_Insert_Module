package com.example.ipodbinsert;

import com.amazonaws.Request;
import com.amazonaws.handlers.RequestHandler;
import com.amazonaws.handlers.RequestHandler2;
import com.amazonaws.util.TimingInfo;
import com.example.ipodbinsert.aws.LambdaHandler;
import com.example.ipodbinsert.ipo.constant.IpoConstants;
import com.example.ipodbinsert.ipo.service.IpoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import javax.naming.Context;

@SpringBootApplication
public class IpoDbInsertApplication extends RequestHandler2 {


    public static void main(String[] args) {
       ApplicationContext context =  SpringApplication.run(IpoDbInsertApplication.class, args);
        LambdaHandler lambdaHandler = context.getBean(LambdaHandler.class);
    }


}
