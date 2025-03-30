package com.mall.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.CommonPageQuery;
import com.mall.common.CommonResult;
import com.mall.model.entity.Speclist;
import com.mall.service.SpeclistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Charlie Zhang(Github: @charlie-zhang-code)
 * @Date: 2025/1/21
 * @Description: 规格管理
 */
@Tag(name = "规格管理")
@Slf4j
@RestController
@RequestMapping("/speclist")
@RequiredArgsConstructor
public class SpeclistController {
    private final SpeclistService speclistService;

    @PostMapping
    @Transactional
    @Operation(summary = "创建规格")
    public CommonResult create(@RequestBody Speclist speclist) {
        boolean save = speclistService.save(speclist);
        if (!save) {
            return new CommonResult(500, "创建失败", false);
        }
        log.info("创建成功");
        return new CommonResult(200, "创建成功", true);
    }

    @PutMapping
    @Operation(summary = "修改规格")
    public CommonResult update(@RequestBody Speclist speclist) {
        boolean update = speclistService.updateById(speclist);
        if (!update) {
            return new CommonResult(500, "修改失败", false);
        }
        log.info("修改成功");
        return new CommonResult(200, "修改成功", true);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除规格")
    public CommonResult delete(@PathVariable Integer id) {
        boolean delete = speclistService.removeById(id);
        if (!delete) {
            return new CommonResult(500, "删除失败", false);
        }
        log.info("删除成功");
        return new CommonResult(200, "删除成功", true);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询规格")
    public CommonResult get(@PathVariable Integer id) {
        Speclist speclist = speclistService.getById(id);
        if (speclist == null) {
            return new CommonResult(500, "查询失败", false);
        }
        log.info("查询成功");
        return new CommonResult(200, "查询成功", speclist);
    }

    @GetMapping("/list")
    @Operation(summary = "查询规格列表")
    public CommonResult list(@ParameterObject CommonPageQuery query) {
        Page<Speclist> speclistPage = new Page<>(query.getPageNum(), query.getPageSize());
        Page<Speclist> page;
        if (StrUtil.isNotBlank(query.getKeyword())) {
            LambdaQueryWrapper<Speclist> speclistLambdaQueryWrapper = new LambdaQueryWrapper<Speclist>()
                    .like(Speclist::getSpecName, query.getKeyword());
            page = speclistService.page(speclistPage, speclistLambdaQueryWrapper);
        } else {
            page = speclistService.page(speclistPage);
        }
        return new CommonResult(200, "查询规格列表成功", page);
    }

    @GetMapping("/goods{id}")
    @Operation(summary = "查询规格列表")
    public CommonResult goodsList(@PathVariable Integer id) {
        LambdaQueryWrapper<Speclist> speclistLambdaQueryWrapper = new LambdaQueryWrapper<Speclist>()
                .eq(Speclist::getGoodsId, id);
        List<Speclist> list = speclistService.list(speclistLambdaQueryWrapper);
        return new CommonResult(200, "查询规格列表成功", list);
    }
}
