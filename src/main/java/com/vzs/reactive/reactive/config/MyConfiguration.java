package com.vzs.reactive.reactive.config;

import com.vzs.reactive.reactive.config.interceptor.CustomRequestMappingHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class MyConfiguration extends WebMvcConfigurationSupport {
    @Bean
    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        CustomRequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();

        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors());
        handlerMapping.setContentNegotiationManager(mvcContentNegotiationManager());
        return handlerMapping;
    }
}
