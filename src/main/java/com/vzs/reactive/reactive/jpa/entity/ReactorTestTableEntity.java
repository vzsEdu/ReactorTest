package com.vzs.reactive.reactive.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reactor_test_table")
@Data
public class ReactorTestTableEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long reactorTestTableId;

    @Column
    private String name;

    @Column
    private String comment;

    @Column(nullable = false)
    private Date createdAt;

}
