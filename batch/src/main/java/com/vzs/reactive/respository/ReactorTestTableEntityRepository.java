package com.vzs.reactive.respository;


import com.vzs.reactive.entity.ReactorTestTableEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReactorTestTableEntityRepository extends CrudRepository<ReactorTestTableEntity, Long> {
    @Query("select max(reactorTestTableId) from ReactorTestTableEntity")
    Long findMaxId();
}
