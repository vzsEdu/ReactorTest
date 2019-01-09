package com.vzs.reactive.reactive.controller;

import com.vzs.reactive.reactive.flux.PublisherExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/publish/example/")
public class PublisherExampleController {

    @Autowired
    private PublisherExample publisherExample;

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        publisherExample.fluxSubscriber();
        return "void";
    }
}
