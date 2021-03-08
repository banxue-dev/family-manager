package com.family.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.family.filter.SourceFilter;


/**
 * @author Administrator
 */
@Configuration
public class SourceConfig {


	Logger logger = LoggerFactory.getLogger(SourceConfig.class);
    @Bean
    public FilterRegistrationBean heFilterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new SourceFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }
    @Bean
    public CorsFilter corsFilter() {
    	logger.debug("开始跨域");
        CorsConfiguration config = new CorsConfiguration();
        //允许所有域名进行跨域调用
        config.addAllowedOrigin("*");
        //允许跨越发送cookie
        config.setAllowCredentials(true);
        //放行全部原始头信息
        config.addAllowedHeader("*");
        //允许所有请求方法跨域调用
        config.addAllowedMethod("*");
        config.setMaxAge(3600l);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
