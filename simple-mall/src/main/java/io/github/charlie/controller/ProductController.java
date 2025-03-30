package io.github.charlie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.charlie.base.Result;
import io.github.charlie.convert.ProductConvert;
import io.github.charlie.model.entity.ProductS;
import io.github.charlie.model.entity.User;
import io.github.charlie.service.ProductSService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 商品控制器
 */
@Tag(name = "商品管理")
@RequestMapping("/api/v1/product")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductSService service;
    private final ProductConvert convert;

//    @Operation(summary = "创建商品")
//    @PostMapping
//    public Result<Boolean> create(@RequestBody ProductForm form) {
//        Product entity = convert.toEntity(form);
//        entity.setId(null);
//        boolean save = service.save(entity);
//        return Result.status(save);
//    }

//    @Operation(summary = "删除商品")
//    @DeleteMapping
//    public Result<Boolean> delete(@RequestBody IDs iDs) {
//        boolean remove = service.removeByIds(iDs.getIds());
//        return Result.status(remove);
//    }

//    @Operation(summary = "修改商品")
//    @PutMapping
//    public Result<Boolean> update(@RequestBody ProductForm form) {
//        Product entity = convert.toEntity(form);
//        boolean update = service.updateById(entity);
//        return Result.status(update);
//    }

//    @Operation(summary = "查询商品")
//    @GetMapping("/{id}")
//    public Result<Product> get(@PathVariable Long id) {
//        Product entity = service.getById(id);
//        return Result.success(entity);
//    }

    @Operation(summary = "查询商品")
    @GetMapping("/{id}")
    public Result<ProductS> get(@PathVariable Long id) {
        ProductS entity = service.getById(id);
        return Result.success(entity);
    }

    @Operation(summary = "查询商品列表")
    @GetMapping("/list")
    public Result<List<ProductS>> list() {
        return Result.success(service.list());
    }

    @Operation(summary = "获取最近的3个商品")
    @GetMapping("/recent")
    public Result<List<ProductS>> getRecent() {
        List<ProductS> list = service.list(new LambdaQueryWrapper<ProductS>()
                .orderByDesc(ProductS::getId)
                .last("limit 12")
        );
        return Result.success(list);
    }

    @Operation(summary = "获取最近的销量3个商品")
    @GetMapping("/sales")
    public Result<List<ProductS>> getSales() {
        List<ProductS> list = service.list(new LambdaQueryWrapper<ProductS>()
                .orderByDesc(ProductS::getSales)
                .last("limit 6")
        );
        return Result.success(list);
    }

    @GetMapping("/search")
    public Result<List<ProductS>> search(@RequestParam String keyword) {
        List<ProductS> list = service.list(new LambdaQueryWrapper<ProductS>()
                .like(ProductS::getName, keyword)
        );
        return Result.success(list);
    }
}
