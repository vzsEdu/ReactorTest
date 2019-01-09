package com.vzs.reactive.partition;

import com.vzs.reactive.respository.ReactorTestTableEntityRepository;
import com.vzs.reactive.utils.PartitionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class MyPartition  implements Partitioner{
    @Autowired
    private ReactorTestTableEntityRepository reactorTestTableEntityRepository;
    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        long lastIndex = getLastIndex();
//        return PartitionUtils.partition(gridSize, lastIndex);
        return PartitionUtils.partition(10, lastIndex);
    }

    private long getLastIndex() {
        Long maxId = reactorTestTableEntityRepository.findMaxId();
        log.info(">>>>>>>>>>>>>>>>Thread {} find last id {} ", Thread.currentThread().getName(), maxId);
        return maxId;
    }
}
