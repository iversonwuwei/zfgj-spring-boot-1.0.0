package com.dlfc.zfgj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by walden on 2017/2/15.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.dlfc.zfgj")
@MapperScan(basePackages = "com.dlfc.zfgj.mapper")
public class ContractApplication {

    public static void main(String[] args){

        SpringApplication.run(ContractApplication.class, args);
    }
}
