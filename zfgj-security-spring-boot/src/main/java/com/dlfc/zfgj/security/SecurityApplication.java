package com.dlfc.zfgj.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by walden on 2017/3/27.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.dlfc.zfgj.security")
public class SecurityApplication {

    public static void main(String[] args){
        SpringApplication.run(SecurityApplication.class, args);
    }
}
