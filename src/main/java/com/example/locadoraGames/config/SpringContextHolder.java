package com.example.locadoraGames.config;

import org.springframework.context.ApplicationContext;

public class SpringContextHolder {
    private static ApplicationContext context;

    public static void setApplicationContext(ApplicationContext ctx) {
        context = ctx;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }
}