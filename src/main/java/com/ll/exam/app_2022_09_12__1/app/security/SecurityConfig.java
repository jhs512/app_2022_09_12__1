package com.ll.exam.app_2022_09_12__1.app.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;

public abstract class SecurityConfig {
    @Autowired
    private OAuth2UserService oAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http = customFilterChain(http);

        http
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/member/login")
                                .loginProcessingUrl("/member/login")
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .loginPage("/member/login")
                                .userInfoEndpoint(userInfoEndpointConfig ->
                                        userInfoEndpointConfig
                                                .userService(oAuth2UserService)
                                )
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .deleteCookies("JSESSIONID")
                )
                .authorizeRequests(
                        authorizeRequests -> authorizeRequests.antMatchers("/**")
                                .permitAll()
                );
        return http.build();
    }

    HttpSecurity customFilterChain(HttpSecurity http) throws Exception {
        return http;
    }
}
