package com.enjoy.Spring.config.resolvers.arguments;

import com.enjoy.Spring.config.resolvers.arguments.annotations.CustomArgumentResolverAnnotation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AnnotationArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        final boolean isSameAnnotationType = parameter.hasParameterAnnotation(CustomArgumentResolverAnnotation.class);
        return isSameAnnotationType;
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter,
                                  final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest,
                                  final WebDataBinderFactory binderFactory) throws Exception {

        String message = webRequest.getParameter("message");
        if (StringUtils.isEmpty(message)) {
            message = "empty message.";
        }

        return message;
    }
}
