package com.mybatisv2.framework.config;

import lombok.Data;

/**
 * 封装了#{}解析出来的参数名称和参数类型
 */
@Data
public class ParameterMappingV2 {
    private String name;
    private Class type;

    public ParameterMappingV2(String name) {
        this.name = name;
    }
}
