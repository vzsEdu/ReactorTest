package com.vzs.reactive.reactive.controller;

import com.google.common.collect.Lists;
import com.vzs.reactive.reactive.config.interceptor.CustomInterceptors;
import com.vzs.reactive.reactive.config.interceptor.MyInterceptor;
import com.vzs.reactive.reactive.controller.vo.MyResponse;
import com.vzs.reactive.reactive.jpa.entity.ReactorTestTableEntity;
import com.vzs.reactive.reactive.jpa.respository.ReactorTestTableEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hello/")
public class HelloController {
    @Autowired
    private ReactorTestTableEntityRepository reactorTestTableEntityRepository;

    @GetMapping("jpa")
    public List<String> helloJpa() {
        List<ReactorTestTableEntity> all = Lists.newArrayList(reactorTestTableEntityRepository.findAll());
        return all.stream().map(ReactorTestTableEntity::getName).collect(Collectors.toList());
    }

    @GetMapping("customer-interceptor")
    @CustomInterceptors(value = {MyInterceptor.class})
    public MyResponse<String> helloCustomerInterceptor(@RequestParam(required = false, defaultValue = "0") Long number) {
        MyResponse<String> response = new MyResponse<>();
        if (number == 0) {
            response.setData("Success");
        } else {
            response.setError("Fail");
        }
        return response;
    }
}
