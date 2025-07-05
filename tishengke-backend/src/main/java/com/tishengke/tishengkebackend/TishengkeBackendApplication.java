package com.tishengke.tishengkebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
// This annotation is used to mark the main class of a Spring Boot application
@MapperScan("com.tishengke.tishengkebackend.infrastructure.mapper")
// This annotation is used to scan for MyBatis mapper interfaces in the specified package
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableAsync
// This annotation is used to enable AspectJ-based automatic proxy creation
public class TishengkeBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(TishengkeBackendApplication.class, args);
    }

}
