package com.ll.exam.app_2022_09_12__1.app.base;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"test"})
public class TestInitData {
    @Bean
    CommandLineRunner initTestData() {
        return args -> {

        };
    }
}
