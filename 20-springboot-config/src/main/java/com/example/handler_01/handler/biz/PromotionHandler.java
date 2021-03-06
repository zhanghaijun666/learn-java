package com.example.handler_01.handler.biz;

import com.example.handler_01.handler.AbstractHandler;
import com.example.handler_01.handler.HandlerType;
import com.example.handler_01.model.OrderDTO;
import org.springframework.stereotype.Component;

/**
 * @Author: 爱做梦的奋斗青年
 * @Date: 2020/11/2 18:58
 * 促销订单处理器
 */
@Component
@HandlerType("3")
public class PromotionHandler extends AbstractHandler {

    @Override
    public String handle(OrderDTO dto) {
        return "处理促销订单";
    }

}
