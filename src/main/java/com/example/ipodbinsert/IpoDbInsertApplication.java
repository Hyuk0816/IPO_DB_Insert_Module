package com.example.ipodbinsert;

import com.amazonaws.Request;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.util.TimingInfo;
import com.example.ipodbinsert.aws.LambdaHandler;
import com.example.ipodbinsert.ipo.constant.IpoConstants;
import com.example.ipodbinsert.ipo.service.IpoService;
import com.example.ipodbinsert.util.exception.SaveFileException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
@RequiredArgsConstructor
public class IpoDbInsertApplication implements RequestHandler<String, String> {

    public final IpoService ipoService;
    @Override
    public String handleRequest(String string, Context context) {
        try {
            ipoService.save();
        } catch (IOException e) {
            throw new SaveFileException(IpoConstants.MESSAGE_400, e);
        }
        return IpoConstants.MESSAGE_200;
    }

    public static void main(String[] args) {
     SpringApplication.run(IpoDbInsertApplication.class, args);

    }


}
