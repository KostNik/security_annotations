package com.oreilly.security.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HttpFilter extends Filter {

    Logger LOG = LoggerFactory.getLogger(HttpFilter.class);

    @Override
    default void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("----- Filter Config {} ------", getName());
        LOG.info("FilterName {}", filterConfig.getFilterName());
        LOG.info("InitParameterNames {}", filterConfig.getInitParameterNames());
        LOG.info("ServletContext {}", filterConfig.getServletContext());
    }

    @Override
    default void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.info("---- Process filter ... {} -----", getName());
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
        LOG.info("---- End Process filter ... {} ----", getName());
    }

    void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

    @Override
    default void destroy() {
        LOG.info("----- Destroy Filter {} -----", getName());
    }

    default String getName() {
        return this.getClass().getName();
    }
}
