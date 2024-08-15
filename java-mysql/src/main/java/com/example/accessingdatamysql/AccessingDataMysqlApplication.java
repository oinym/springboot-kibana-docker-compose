package com.example.accessingdatamysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories("com.example.accessingdatamysql.jparepositories")
public class AccessingDataMysqlApplication {
    
    public static void main(String[] args) {
        
        SpringApplication.run(AccessingDataMysqlApplication.class, args);
    }
}
