package com.enjoy.Spring.config;

import com.enjoy.Spring.config.interceptors.SessionInterceptor;
import com.enjoy.Spring.config.resolvers.arguments.AnnotationArgumentResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${project.switch.interceptor}")
    private boolean interceptorSwitch;

    private final AnnotationArgumentResolver annotationArgumentResolver;

    public WebMvcConfig(final AnnotationArgumentResolver annotationArgumentResolver) {
        this.annotationArgumentResolver = annotationArgumentResolver;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        if (interceptorSwitch) {
            registry.addInterceptor(new SessionInterceptor())
                    .addPathPatterns("/**")
                    .excludePathPatterns("/exclude/**");
        }
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(annotationArgumentResolver);
    }
}
