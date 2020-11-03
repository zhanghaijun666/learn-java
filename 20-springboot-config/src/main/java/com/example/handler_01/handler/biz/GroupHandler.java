package com.example.handler_01.handler.biz;

import com.example.handler_01.handler.AbstractHandler;
import com.example.handler_01.handler.HandlerType;
import com.example.handler_01.model.OrderDTO;
import org.springframework.stereotype.Component;

/**
 * @Author: 爱做梦的奋斗青年
 * @Date: 2020/11/2 18:58
 * 团购订单处理器
 */
@Component
@HandlerType("2")
public class GroupHandler extends AbstractHandler {

    @Override
    public String handle(OrderDTO dto) {
        return "处理团购订单";
    }

}
