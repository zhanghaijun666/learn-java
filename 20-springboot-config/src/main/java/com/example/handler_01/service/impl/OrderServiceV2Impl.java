package com.example.handler_01.service.impl;

import com.example.handler_01.handler.AbstractHandler;
import com.example.handler_01.handler.HandlerContext;
import com.example.handler_01.model.OrderDTO;
import com.example.handler_01.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV2Impl implements IOrderService {

    @Autowired
    private HandlerContext handlerContext;

    @Override
    public String handle(OrderDTO dto) {
        AbstractHandler handler = handlerContext.getInstance(dto.getType());
        return handler.handle(dto);
    }

}
