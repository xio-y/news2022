package com.edu.hbpu.news2022;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.edu.hbpu.news2022.mapper")
public class News2022Application {

    public static void main(String[] args) {
        SpringApplication.run(News2022Application.class, args);
    }

}
