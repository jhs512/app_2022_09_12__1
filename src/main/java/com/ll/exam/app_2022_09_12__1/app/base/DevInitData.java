package com.ll.exam.app_2022_09_12__1.app.base;


import com.ll.exam.app_2022_09_12__1.app.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev"})
public class DevInitData {
    @Bean
    CommandLineRunner initDevData(MemberService memberService) {
        return args -> {
            memberService.join("user1", "1234", "user1@test.com", null);
            memberService.join("user2", "1234", "user2@test.com", null);
            memberService.join("user3", "1234", "user3@test.com", null);
        };
    }
}
