package com.vzs.reactive.reactive.controller;

import com.vzs.reactive.reactive.controller.vo.ReactorTestTableVo;
import com.vzs.reactive.reactive.service.ReactorTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/flux/jpa/")
public class FluxJapController {
    @Autowired
    private ReactorTableService reactorTableService;

    @GetMapping("testReactorFlux")
    public Flux<ReactorTestTableVo> fluxTest() {
        return reactorTableService.findAll();
    }

}
