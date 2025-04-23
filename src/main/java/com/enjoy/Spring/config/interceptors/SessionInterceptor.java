package com.enjoy.Spring.config.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class SessionInterceptor implements HandlerInterceptor {

    private static final String INTERCEPTOR_TAG = "[INTERCEPTOR]";

    /* Controller 호출 전 실행 */
    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {

        final String threadUuid = request.getAttribute("threadUUID")
                                   .toString();
        log.info("### -->| {} {} preHandle", threadUuid, INTERCEPTOR_TAG);

        return true;
    }

    /* Controller 호출 후 실행 */
    @Override
    public void postHandle(final HttpServletRequest request,
                           final HttpServletResponse response,
                           final Object handler,
                           final ModelAndView modelAndView) throws Exception {

        final String threadUuid = request.getAttribute("threadUUID")
                                         .toString();
        log.info("### -->  {} {} postHandle", threadUuid, INTERCEPTOR_TAG);
    }

    /* 모든 작업이 완료된 후 실행 */
    @Override
    public void afterCompletion(final HttpServletRequest request,
                                final HttpServletResponse response,
                                final Object handler,
                                final Exception ex) throws Exception {

        final String threadUuid = request.getAttribute("threadUUID")
                                         .toString();
        log.info("### |<-- {} {} afterCompletion", threadUuid, INTERCEPTOR_TAG);
    }
}
