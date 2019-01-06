package com.vzs.reactive.reactive.controller.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ReactorTestTableVo {
    private Long reactorTestTableId;

    private String name;

    private String comment;

    private Date createdAt;

}
