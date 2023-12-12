package com.ngr.ngrbooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
public class NgrBooksApplication {

    public static void main(String[] args) {
        SpringApplication.run(NgrBooksApplication.class, args);
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }


}
