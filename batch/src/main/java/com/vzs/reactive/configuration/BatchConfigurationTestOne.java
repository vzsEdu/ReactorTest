package com.vzs.reactive.configuration;

import com.vzs.reactive.entity.ReactorTestTableEntity;
import com.vzs.reactive.partition.MyPartition;
import com.vzs.reactive.partition.MyTaskLet;
import com.vzs.reactive.processor.ProcessorTestOne;
import com.vzs.reactive.reader.ReaderTest;
import com.vzs.reactive.writer.WriterTestOne;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfigurationTestOne {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ReaderTest readerTest;
    @Autowired
    private ProcessorTestOne processorTestOne;
    @Autowired
    private WriterTestOne writerTestOne;

    // partition
    @Autowired
    private MyPartition myPartition;
    @Autowired
    private MyTaskLet myTaskLet;
    @Bean
    public Step step1Master(TaskExecutor taskExecutor) {
        return stepBuilderFactory.get("step1.master")
                .partitioner("step1", myPartition)
                .partitionHandler(partitionHandler(taskExecutor))
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor("spring_batch");
    }

    @Bean
    public PartitionHandler partitionHandler(TaskExecutor taskExecutor) {
        TaskExecutorPartitionHandler retVal = new TaskExecutorPartitionHandler();
        retVal.setTaskExecutor(taskExecutor);
        retVal.setStep(step1());
        retVal.setGridSize(2);
        return retVal;
    }
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(myTaskLet)
                .build();
    }

    @Bean
    public Job multilineJob(JobListener jobListener, Step step1Master) throws Exception {
        return jobBuilderFactory.get("read_and_write")
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
                .start(step1Master)
                .build();
    }


//    @Bean
//    public TaskExecutor taskExecutor(){
//        return new SimpleAsyncTaskExecutor("spring_batch");
//    }
    @Bean
    public Step step2(TaskExecutor taskExecutor) {
        return stepBuilderFactory.get("step1")
                .<List<ReactorTestTableEntity>,List<ReactorTestTableEntity>>chunk(2)
                .reader(readerTest)
                .processor(processorTestOne)
                .writer(writerTestOne)
                .taskExecutor(taskExecutor)
//                .stream(readerJdbc)
                .build();
    }
//    @Bean
//    public Job multilineJob(JobListener jobListener, Step step1) throws Exception {
//        return jobBuilderFactory.get("read_and_write")
//                .incrementer(new RunIdIncrementer())
//                .listener(jobListener)
//                .start(step1)
//                .build();
//    }

}
