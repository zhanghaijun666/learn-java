package com.example.handler_01.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: CipherCui
 * @Description:
 * @Date: Created in 9:48 2019/2/2
 */
@Data
public class OrderDTO {

    private String code;

    private BigDecimal price;

    /**
     * 订单类型
     * 1：普通订单；
     * 2：团购订单；
     * 3：促销订单；
     */
    private String type;
}
