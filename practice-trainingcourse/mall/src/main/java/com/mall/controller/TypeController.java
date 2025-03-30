package com.mall.controller;

import com.mall.common.CommonPageQuery;
import com.mall.common.CommonResult;
import com.mall.common.Option;
import com.mall.model.entity.Type;
import com.mall.service.TypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Charlie Zhang(Github: @charlie-zhang-code)
 * @Date: 2025/1/21
 * @Description: 类型管理
 */
@Tag(name = "类型管理")
@Slf4j
@RestController
@RequestMapping("/type")
@RequiredArgsConstructor
public class TypeController {
    private final TypeService typeService;

    @PostMapping
    @Transactional
    @Operation(summary = "创建类型")
    public CommonResult create(@RequestBody Type type) {
        boolean save = typeService.save(type);
        if (!save) {
            return new CommonResult(500, "创建失败", false);
        }
        log.info("创建成功");
        return new CommonResult(200, "创建成功", true);
    }

    @PutMapping
    @Operation(summary = "修改类型")
    public CommonResult update(@RequestBody Type type) {
        boolean update = typeService.updateById(type);
        if (!update) {
            return new CommonResult(500, "修改失败", false);
        }
        log.info("修改成功");
        return new CommonResult(200, "修改成功", true);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除类型")
    public CommonResult delete(@PathVariable Integer id) {
        boolean delete = typeService.removeById(id);
        if (!delete) {
            return new CommonResult(500, "删除失败", false);
        }
        log.info("删除成功");
        return new CommonResult(200, "删除成功", true);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询类型")
    public CommonResult get(@PathVariable Integer id) {
        Type type = typeService.getById(id);
        if (type == null) {
            return new CommonResult(500, "查询失败", false);
        }
        log.info("查询成功");
        return new CommonResult(200, "查询成功", type);
    }

    @GetMapping("/list")
    @Operation(summary = "查询类型列表")
    public CommonResult list() {
        return new CommonResult(200, "查询成功", typeService.list());
    }

    @GetMapping("/option")
    @Operation(summary = "查询类型下拉列表")
    public CommonResult option() {
        return new CommonResult(200, "查询成功", typeService.list().stream().map(type -> new Option<Integer>(type.getId(), type.getName())).toList());
    }
}
