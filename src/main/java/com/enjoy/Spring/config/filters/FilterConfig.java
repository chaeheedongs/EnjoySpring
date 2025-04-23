package com.enjoy.Spring.config.filters;

import com.enjoy.Spring.config.filters.filter.LoggingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Value("${project.switch.filter}")
    private boolean filterSwitch;

    @Bean
    public FilterRegistrationBean loggingFilter() {
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setEnabled(filterSwitch);
        registration.setFilter(new LoggingFilter());
        registration.setName("loggingFilter");
        registration.setOrder(1);

        return registration;
    }
}
