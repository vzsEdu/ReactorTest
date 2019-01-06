package com.vzs.reactive.reactive.service;

import com.google.common.collect.Lists;
import com.vzs.reactive.reactive.controller.vo.ReactorTestTableVo;
import com.vzs.reactive.reactive.jpa.entity.ReactorTestTableEntity;
import com.vzs.reactive.reactive.jpa.respository.ReactorTestTableEntityRepository;
import com.vzs.reactive.reactive.jpa.respository.ReactorTestTableFluxRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReactorTableService {
    @Autowired
    private ReactorTestTableEntityRepository reactorTestTableEntityRepository;

    public Flux<ReactorTestTableVo> findAll() {
//        Flux<ReactorTestTableEntity> all = reactorTestTableEntityRepository.findAll();
//        return all.map(item -> {
//            ReactorTestTableVo reactorTestTableVo = new ReactorTestTableVo();
//            BeanUtils.copyProperties(item, reactorTestTableVo);
//            return reactorTestTableVo;
//        });

        return Flux.create(reactorTestTableVoFluxSink -> {
            reactorTestTableEntityRepository.findAll().forEach(item -> {
                reactorTestTableVoFluxSink.next(transfer(item));
            });
            reactorTestTableVoFluxSink.complete();
        });
    }

    private ReactorTestTableVo transfer(ReactorTestTableEntity reactorTestTableEntity) {
            ReactorTestTableVo reactorTestTableVo = new ReactorTestTableVo();
            BeanUtils.copyProperties(reactorTestTableEntity, reactorTestTableVo);
            return reactorTestTableVo;
    }
}
