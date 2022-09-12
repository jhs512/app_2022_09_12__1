package com.ll.exam.app_2022_09_12__1.app.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AppConfig {
    private static Environment environment;

    private static String getActiveProfile() {
        return environment.getActiveProfiles()[0];
    }

    public static boolean isNotProd() {
        return isProd() == false;
    }

    public static boolean isProd() {
        return getActiveProfile().equals("prod");
    }

    public static boolean isNotDev() {
        return isLocal() == false;
    }

    public static boolean isLocal() {
        return getActiveProfile().equals("local");
    }

    public static boolean isNotTest() {
        return isLocal() == false;
    }

    public static boolean isTest() {
        return getActiveProfile().equals("test");
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        AppConfig.environment = environment;
    }
}
