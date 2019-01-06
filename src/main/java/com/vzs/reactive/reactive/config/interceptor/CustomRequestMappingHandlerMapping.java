package com.vzs.reactive.reactive.config.interceptor;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
	@Override
	protected HandlerExecutionChain getHandlerExecutionChain(Object handler, HttpServletRequest request) {
		HandlerExecutionChain chain = super.getHandlerExecutionChain(handler, request);
		HandlerInterceptor[] interceptors = detectInterceptors(((HandlerMethod) chain.getHandler()).getMethod());
		if (interceptors != null && interceptors.length > 0) {
			chain.addInterceptors(interceptors);
		}

		return chain;
	}

	protected HandlerInterceptor[] detectInterceptors(Method method) {
		CustomInterceptors customInterceptorAnnotions = AnnotationUtils.findAnnotation(method, CustomInterceptors.class);
		
		List<HandlerInterceptor> interceptors = new ArrayList<>();
		if (customInterceptorAnnotions != null) {
			Class[] interceptorClasses = customInterceptorAnnotions.value();
			if (interceptorClasses != null) {
				for (Class interceptorClass : interceptorClasses) {
					interceptors.add((HandlerInterceptor) this.getWebApplicationContext().getBean(interceptorClass));
				}
			}
		}

		int count = interceptors.size();
		return (count > 0) ? interceptors.toArray(new HandlerInterceptor[count]) : null;
	}
}