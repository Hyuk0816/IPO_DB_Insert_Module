package com.example.ipodbinsert.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.ipodbinsert.ipo.constant.IpoConstants;
import com.example.ipodbinsert.ipo.service.IpoService;
import com.example.ipodbinsert.util.exception.SaveFileException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class LambdaHandler implements RequestHandler<String, String> {

    private final IpoService ipoService;

    @Override
    public String handleRequest(String string, Context context) {
        try {
            ipoService.save();
        } catch (IOException e) {
            throw new SaveFileException(IpoConstants.MESSAGE_400, e);
        }
        return IpoConstants.MESSAGE_200;
    }
}
