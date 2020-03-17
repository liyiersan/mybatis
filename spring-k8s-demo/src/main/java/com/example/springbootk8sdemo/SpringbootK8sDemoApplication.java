package com.example.springbootk8sdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@RestController
public class SpringbootK8sDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootK8sDemoApplication.class, args);
    }

    @RequestMapping("/test")
    public String test(){
        return "1. Hello world ! " + new SimpleDateFormat(" [yyyy-mm-dd  HH:mm:ss]").format(new Date());
    }
}
