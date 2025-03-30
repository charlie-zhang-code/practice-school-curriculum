package io.github.charlie.controller;

import io.github.charlie.base.IDs;
import io.github.charlie.base.Result;
import io.github.charlie.convert.OrderConvert;
import io.github.charlie.model.entity.Comment;
import io.github.charlie.model.entity.Order;
import io.github.charlie.model.form.CommentForm;
import io.github.charlie.model.form.OrderForm;
import io.github.charlie.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 订单控制器
 */
@Tag(name = "订单管理")
@RequestMapping("/api/v1/order")
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    private final OrderConvert convert;

    @Operation(summary = "创建订单")
    @PostMapping
    public Result<Boolean> create(@RequestBody OrderForm form) {
        Order entity = convert.toEntity(form);
        entity.setId(null);
        boolean save = service.save(entity);
        return Result.status(save);
    }

    @Operation(summary = "删除订单")
    @DeleteMapping
    public Result<Boolean> delete(@RequestBody IDs iDs) {
        boolean remove = service.removeByIds(iDs.getIds());
        return Result.status(remove);
    }

    @Operation(summary = "修改订单")
    @PutMapping
    public Result<Boolean> update(@RequestBody OrderForm form) {
        Order entity = convert.toEntity(form);
        boolean update = service.updateById(entity);
        return Result.status(update);
    }

    @Operation(summary = "查询订单")
    @GetMapping("/{id}")
    public Result<Order> get(@PathVariable Long id) {
        Order entity = service.getById(id);
        return Result.success(entity);
    }
}
