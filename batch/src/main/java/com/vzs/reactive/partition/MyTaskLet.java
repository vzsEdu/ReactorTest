package com.vzs.reactive.partition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import static com.vzs.reactive.utils.PartitionUtils.END_INDEX_PARAM_NAME;
import static com.vzs.reactive.utils.PartitionUtils.START_INDEX_PARAM_NAME;

@Component
@Slf4j
public class MyTaskLet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        int startIdx = chunkContext.getStepContext().getStepExecution().getExecutionContext().getInt(START_INDEX_PARAM_NAME);
        int endIdx = chunkContext.getStepContext().getStepExecution().getExecutionContext().getInt(END_INDEX_PARAM_NAME);
        log.info("Thread Id {} Start {}, END {} ", Thread.currentThread().getName(), startIdx, endIdx);
        return RepeatStatus.FINISHED;
    }
}
