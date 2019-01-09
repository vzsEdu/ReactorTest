package com.vzs.reactive.writer;

import com.vzs.reactive.entity.ReactorTestTableEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class WriterTestOne implements ItemWriter<List<ReactorTestTableEntity>> {
    @Override
    public void write(List<? extends List<ReactorTestTableEntity>> items) throws Exception {
        log.info("writer {} {} ", Thread.currentThread().getId(), items);
    }
}
