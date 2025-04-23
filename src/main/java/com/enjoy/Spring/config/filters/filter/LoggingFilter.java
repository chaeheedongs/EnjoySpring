package com.enjoy.Spring.config.filters.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public final class LoggingFilter implements Filter {

    private static final String FILTER_TAG = "[FILTER]";
    private static final String REQUEST_TAG = FILTER_TAG + "[REQUEST]";
    private static final String RESPONSE_TAG = FILTER_TAG + "[RESPONSE]";

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        log.info("### {} {} init", FILTER_TAG, filterConfig.getFilterName());
    }

    @Override
    public void destroy() {
        log.info("### {} LoggingFilter destroy", FILTER_TAG);
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain) throws IOException, ServletException {

        final String uuid = "[" + UUID.randomUUID()
                                      .toString()
                                      .replaceAll("-", "")
                                      .substring(0, 9) + "]";
        request.setAttribute("threadUUID", uuid);

        this.requestPrintLog(request, uuid);
        chain.doFilter(request, response);
        this.responsePrintLog(response, uuid);
    }

    private void requestPrintLog(final ServletRequest request,
                                 final String uuid) {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final String method = httpServletRequest.getMethod();
        final String uri = httpServletRequest.getRequestURI();

        log.info("### --> {} {} {} {}", uuid, REQUEST_TAG, method, uri);
    }

    private void responsePrintLog(final ServletResponse response,
                                  final String uuid) {
        final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        final HttpStatus httpStatus = HttpStatus.valueOf(httpServletResponse.getStatus());

        log.info("### <-- {} {} {}", uuid, RESPONSE_TAG, httpStatus);
    }
}
