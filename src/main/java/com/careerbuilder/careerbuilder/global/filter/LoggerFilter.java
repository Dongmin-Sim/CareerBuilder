package com.careerbuilder.careerbuilder.global.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        filterChain.doFilter(req, res);


        // Log Request
        var reqHeaderValues = new StringBuilder();
        req.getHeaderNames().asIterator().forEachRemaining(headerKey -> {
            String headerValue = req.getHeader(headerKey);
            reqHeaderValues.append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append("]");
        });

        var reqUri = req.getRequestURI();
        var reqMethod = req.getMethod();
        var reqBody = new String(req.getContentAsByteArray());
        log.info("[Request ] uri : {}, method : {}, header : {}, body : {}", reqUri, reqMethod, reqHeaderValues, reqBody);


        // Log Response
        var resHeaderValues = new StringBuilder();
        res.getHeaderNames().forEach(headerKey -> {
            var headerValue = res.getHeader(headerKey);
            resHeaderValues.append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append("]");
        });

        var resBody = new String(res.getContentAsByteArray());
        log.info("[Response] uri : {}, method : {}, header : {}, body : {}", reqUri, reqMethod, resHeaderValues, resBody);

        res.copyBodyToResponse();
    }
}
