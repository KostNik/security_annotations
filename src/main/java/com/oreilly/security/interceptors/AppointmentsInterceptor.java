package com.oreilly.security.interceptors;

import com.oreilly.security.domain.entities.postprocessors.LogUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppointmentsInterceptor implements HandlerInterceptor {

   private final static Logger LOG = LoggerFactory.getLogger(AppointmentsInterceptor.class);

    @Override
    public boolean preHandle(@LogUrl HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("PRE handle");
        return false;
    }

    @Override
    public void postHandle(@LogUrl HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOG.info("POST handle");
    }

    @Override
    public void afterCompletion(@LogUrl HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOG.info("AFTER Completion");
    }
}
