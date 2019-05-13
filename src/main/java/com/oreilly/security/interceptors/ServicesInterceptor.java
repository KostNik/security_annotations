package com.oreilly.security.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

public class ServicesInterceptor implements WebRequestInterceptor {

    private final static Logger LOG = LoggerFactory.getLogger(ServicesInterceptor.class);

    @Override
    public void preHandle(WebRequest request) throws Exception {
        LOG.info("Pre handle");
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        LOG.info("Post handle");
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        LOG.info("After Completion");
    }
}
