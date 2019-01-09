package com.vzs.reactive.processor;

import com.vzs.reactive.entity.ReactorTestTableEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ProcessorTestOne implements ItemProcessor<List<ReactorTestTableEntity>, List<ReactorTestTableEntity>> {

    @Override
    public List<ReactorTestTableEntity> process(List<ReactorTestTableEntity> item) throws Exception {
        log.info("In the process {} {}", Thread.currentThread().getId(), item);
        return item;
    }
}
