package com.example.multitenancydifferentdbtraining.tenant;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

@Component
public class TenantInterceptor implements WebRequestInterceptor {

    private static final String TENANT_HEADER = "X-tenant";
    
    @Override
    public void preHandle(WebRequest request) throws Exception {
        TenantContext.setTenantId(request.getHeader(TENANT_HEADER));
        System.out.println("Pre handle, set tenant id: "+ request.getHeader(TENANT_HEADER));
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        System.out.println("Request completed");
    }
}
