package io.hbprotoss.web.interceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by hbprotoss on 11/13/15.
 */
public class TimerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory
            .getLogger(TimerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug(request.getRequestURI());
        request.setAttribute("_startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long internal = System.currentTimeMillis() - (Long) request.getAttribute("_startTime");
        logger.debug("Request URL: " + request.getRequestURL().toString() + " Handler Time Cost: " + internal + " ms");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long internal = System.currentTimeMillis() - (Long) request.getAttribute("_startTime");
        logger.debug("Request URL: " + request.getRequestURL().toString() + " Render Time Cost: " + internal + " ms");
    }
}
