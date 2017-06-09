package com.dlfc.zfgj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by walden on 2017/2/22.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.dlfc.zfgj")
@MapperScan(basePackages = "com.dlfc.zfgj.mapper")
public class HouseResourceApplication {

    public static void main(String[] args){
        SpringApplication.run(HouseResourceApplication.class, args);
    }
}
