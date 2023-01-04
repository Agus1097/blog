package com.prueba.blog.security;

import com.prueba.blog.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_DATE = 86400000; // 1 day
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
    }
}
