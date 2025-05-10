package com.enjoy.Spring.config.resolvers.arguments;

import com.enjoy.Spring.config.resolvers.arguments.annotations.CustomArgumentResolverAnnotation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 기능 요약:
 * 컨트롤러 내부의 메서드에서 파라미터를 받을 때 처리하고자 하는 어노테이션들의 내부 기능
 * [ @RequestParam, @PathVariable ]
 *
 * 작동 순서:
 * Interceptor -> ArgumentResolver -> MessageConverter -> Controller
 */
@Component
public class AnnotationArgumentResolver implements HandlerMethodArgumentResolver {

    /* 커스텀 어노테이션과 매칭 여부 확인 (false일 경우 아래 로직 패스함) */
    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        final boolean isSameAnnotationType = parameter.hasParameterAnnotation(CustomArgumentResolverAnnotation.class);
        return isSameAnnotationType;
    }

    /* 위 supportsParameter 조건이 true일 경우 아래 메서드 로직을 수행하여,
    * 컨트롤러에 값을 전달한다. */
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
