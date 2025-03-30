package io.github.charlie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.charlie.model.entity.Order;
import io.github.charlie.service.OrderService;
import io.github.charlie.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author Charlie
* @description 针对表【sys_order(评论表)】的数据库操作Service实现
* @createDate 2025-02-05 16:51:42
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




