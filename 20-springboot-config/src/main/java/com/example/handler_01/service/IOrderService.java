package com.example.handler_01.service;


import com.example.handler_01.model.OrderDTO;

public interface IOrderService {

    /**
     * 根据订单的不同类型作出不同的处理
     *
     * @param dto 订单实体
     * @return 为了简单，返回字符串
     */
    String handle(OrderDTO dto);

}
