package com.example.hello.web;

import com.example.hello.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger;

    public void logic(String id) {
//        MyLogger myLogger = myLoggerProvider.getObject();

        myLogger.log("service id = " + id);
    }
}
