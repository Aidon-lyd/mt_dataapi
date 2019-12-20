package com.qianfeng;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName CorsConfig
 * @Author lyd
 * @Date $ $
 * @Vesion 1.0
 * @Description 解决Failed to load http://10.0.16.22:8080/listStatsUser/2018-08-17: No 'Access-Control-Allow-Origin' header is present on the requested resource.
 * Origin 'null' is therefore not allowed access.
 **/
@Configuration
public class CorsConfig  extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }
}