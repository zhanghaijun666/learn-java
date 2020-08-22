package com.mybatisv3.config;

import lombok.Data;

/**
 * 封装了#{}解析出来的参数名称和参数类型
 */
@Data
public class ParameterMapping {
    private String name;
    private Class type;

    public ParameterMapping(String name) {
        this.name = name;
    }
}
