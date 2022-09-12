package com.ll.exam.app_2022_09_12__1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class App202209121Application {

    public static void main(String[] args) {
        SpringApplication.run(App202209121Application.class, args);
    }

}
