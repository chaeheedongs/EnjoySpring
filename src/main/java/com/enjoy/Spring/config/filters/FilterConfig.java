package com.enjoy.Spring.config.filters;

import com.enjoy.Spring.config.filters.filter.LoggingFilter;
import com.enjoy.Spring.config.filters.types.UrlPattern;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean loggingFilter() {
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoggingFilter());
        registration.addUrlPatterns(UrlPattern.ALL.getCode());
        registration.setName("loggingFilter");
        registration.setOrder(1);
        return registration;
    }
}
