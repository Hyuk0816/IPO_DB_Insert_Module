package com.example.ipodbinsert;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.ipodbinsert.ipo.constant.IpoConstants;
import com.example.ipodbinsert.ipo.service.IpoService;
import com.example.ipodbinsert.util.exception.SaveFileException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class IpoDbInsertApplication {




    public static void main(String[] args) {
      SpringApplication.run(IpoDbInsertApplication.class, args);

    }


}
