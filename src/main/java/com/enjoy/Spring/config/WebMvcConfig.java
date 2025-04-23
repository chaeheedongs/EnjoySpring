package com.enjoy.Spring.config;

import com.enjoy.Spring.config.interceptors.SessionInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${project.switch.interceptor}")
    private boolean interceptorSwitch;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        if (interceptorSwitch) {
            registry.addInterceptor(new SessionInterceptor())
                    .addPathPatterns("/**")
                    .excludePathPatterns("/exclude/**");
        }
    }
}
