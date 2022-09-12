package com.ll.exam.app_2022_09_12__1.app.base;


import com.ll.exam.app_2022_09_12__1.app.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;

@Configuration
@EnableWebSecurity
@Profile({"dev", "test"})
public class DevAndTestSecurityConfig extends SecurityConfig {

    HttpSecurity customFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable();
    }
}
