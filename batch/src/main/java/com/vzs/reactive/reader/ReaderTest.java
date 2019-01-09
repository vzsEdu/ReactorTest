package com.vzs.reactive.reader;

import com.google.common.collect.Lists;
import com.vzs.reactive.entity.ReactorTestTableEntity;
import com.vzs.reactive.respository.ReactorTestTableEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.batch.item.ItemReader;
import java.io.Serializable;
import java.util.List;

@Component
@Slf4j
@StepScope
public class ReaderTest implements ItemReader<List<ReactorTestTableEntity>> {
    private boolean fetched = false;
    private int count = 0;
    @Autowired
    private ReactorTestTableEntityRepository reactorTestTableEntityRepository;
    @Override
    public List<ReactorTestTableEntity> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (count > 10) return null;
        fetched = true;
        count++;
        log.info("In the reader {}", Thread.currentThread().getId());
        return Lists.newArrayList(reactorTestTableEntityRepository.findAll());
    }
}
