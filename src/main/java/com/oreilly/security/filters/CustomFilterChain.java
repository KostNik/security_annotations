package com.oreilly.security.filters;

import io.vavr.collection.Stream;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomFilterChain implements HttpFilter {

    private final static Logger LOG = LoggerFactory.getLogger(CustomFilterChain.class);

    @Autowired
    private UrlPathHelper urlPathHelper;

    private List<Filter> filters;

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestUri = urlPathHelper.getRequestUri(request);

        LOG.info("Start chain");
        LOG.info("RequestUri {}", requestUri);
        Stream.ofAll(filters).forEach(filter -> Try.run(() -> filter.doFilter(request, response, chain)));
        chain.doFilter(request, response);
        LOG.info("End chain");
    }


    @Override
    public void destroy() {
        LOG.info("Destroy chain");
    }


    @Required
    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }
}
