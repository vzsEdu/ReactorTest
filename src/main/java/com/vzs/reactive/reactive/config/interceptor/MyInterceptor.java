package com.vzs.reactive.reactive.config.interceptor;

import com.vzs.reactive.reactive.controller.vo.MyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("pre handle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod)handler;
            log.info(method.toString());

        }
//        if (handler instanceof MyResponse) {
//            MyResponse myResponse = (MyResponse) handler;
//            log.info("from interceptor " + myResponse.getData());
//            log.info("from interceptor error " + myResponse.getError());
//        }
        log.info("post handle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
    }
}
