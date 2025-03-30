package com.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.model.entity.Orders;
import com.mall.service.OrdersService;
import com.mall.mapper.OrdersMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【orders】的数据库操作Service实现
* @createDate 2025-01-21 13:48:44
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

}




