package com.vzs.reactive.reactive.controller.vo;

import lombok.Data;

@Data
public class MyResponse<T> {
    private T data;
    private String error;
}
