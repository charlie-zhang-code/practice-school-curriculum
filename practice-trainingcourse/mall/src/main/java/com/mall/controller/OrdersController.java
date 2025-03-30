package com.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.CommonResult;
import com.mall.model.entity.Orders;
import com.mall.model.query.MessageQuery;
import com.mall.service.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;


/**
 * @author 李茂鑫
 * @Date 2025/1/21 13:49
 * @Descripition
 */
@RestController
@RequestMapping("/orders")
@CrossOrigin
@Tag(name = "订单管理")
public class OrdersController {
    private static final Logger log = LoggerFactory.getLogger(OrdersController.class);
    @Autowired
    private OrdersService orderService;

    /**
     * 添加订单
     * @param orders
     * @return
     */
    @PostMapping("/add")
    @Operation(summary = "增加订单")
    public CommonResult createOrder( @RequestBody Orders orders) {
        // TODO 【优化方向】 08 参数过多的问题
        try {
            boolean createOrder = orderService.save(orders);
            if (createOrder) {
                return CommonResult.success(orders);
            } else {
                return CommonResult.error("添加失败");
            }
        } catch (IllegalArgumentException e) {
            // 处理非法参数异常
            return CommonResult.error("非法参数: " + e.getMessage());
        } catch (DataAccessException e) {
            // 处理数据库访问异常
            log.error("数据库访问错误", e);
            return CommonResult.error("数据库访问错误，请稍后再试");
        } catch (Exception e) {
            // 处理其他未知异常
            return CommonResult.error("系统错误，请稍后再试");
        }
    }


     /**
     * 删除订单
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除订单")
    @CrossOrigin
    public CommonResult deleteOrder(@PathVariable String id) {
        try {
            // 调用删除订单的方法
            boolean deleteOrder = orderService.removeById(id);

            // 根据删除结果返回不同的响应
            if (deleteOrder) {
                return CommonResult.success("删除成功");
            } else {
                return CommonResult.error("订单不存在或删除失败");
            }
        } catch (IllegalArgumentException e) {

            return CommonResult.error("无效的订单ID");
        } catch (DataAccessException e) {
            // 针对数据库访问的异常处理
            return CommonResult.error("数据库操作失败，请稍后重试");
        } catch (Exception e) {

            return CommonResult.error("系统错误，请稍后再试");
        }
    }

    /**
     * 更新订单
     * @param id
     * @param orders
     * @return
     */
    @PutMapping("/{id}")
    @Operation(summary = "更新订单")
    @CrossOrigin
    public CommonResult updateOrder(@PathVariable Long id, @RequestBody Orders orders) {
        // TODO 【优化方向】 09 方法参数设计可以优化，因为更新会携带id回来，不用再设置id了，同时，要考虑参数过多的问题
        try {
            // 调用更新订单的方法
            boolean updateOrder = orderService.updateById(orders);

            // 根据更新结果返回不同的响应
            if (updateOrder) {
                return CommonResult.success("更新成功");
            } else {
                return CommonResult.error("订单不存在或更新失败");
            }
        } catch (IllegalArgumentException e) {

            return CommonResult.error("无效的订单ID");

        } catch (Exception e) {
            return CommonResult.error("系统错误，请稍后再试");
        }
    }

    /**
     * 查询订单
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "查询订单")
    @CrossOrigin
    public CommonResult getOrderById(@PathVariable String id) {
        try {
            // 调用查询订单的方法
            Orders order = orderService.getById(id);

            // 根据查询结果返回不同的响应
            if (order != null) {
                // 返回查询到的订单数据
                return CommonResult.success(order);
            } else {
                return CommonResult.error("订单不存在");
            }
        } catch (IllegalArgumentException e) {
            return CommonResult.error("无效的订单ID");
        } catch (Exception e) {
            return CommonResult.error("系统错误，请稍后尝试");
        }
    }


    /**
     * 分页查询所有订单
     * @param MessageQuery
     * @return
     */
    @GetMapping("/orders")
    @Operation(summary = "分页查询所有订单")
    @CrossOrigin
    public CommonResult listOrders(MessageQuery MessageQuery) {
        // TODO 【优化方向】 10 这里参数是不是使用错了呢？
        try {
            // 调用分页查询订单的方法
            Page<Orders> page = new Page<>(MessageQuery.getPageNo(), MessageQuery.getPageSize());
            QueryWrapper<Orders> wrapper = new QueryWrapper<>();

            IPage<Orders> ordersPage = orderService.page(page, wrapper);


            // 返回分页查询结果
            return CommonResult.success(ordersPage);
        } catch (Exception e) {
            return CommonResult.error("系统错误，请稍后再试");
        }
    }
}

