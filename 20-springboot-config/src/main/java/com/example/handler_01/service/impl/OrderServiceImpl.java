package com.example.handler_01.service.impl;

import com.example.handler_01.model.OrderDTO;
import com.example.handler_01.service.IOrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {

    @Override
    public String handle(OrderDTO dto) {
        String type = dto.getType();
        if ("1".equals(type)) {
            return "处理普通订单";
        } else if ("2".equals(type)) {
            return "处理团购订单";
        } else if ("3".equals(type)) {
            return "处理促销订单";
        }
        return null;
    }

}
