package com.heimdall;

import com.heimdall.controller.RestExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({RestExceptionHandler.class})
public class HeimdallLauncher {

    public static void main(String[] args) {
        SpringApplication.run(HeimdallLauncher.class, args);
    }
}