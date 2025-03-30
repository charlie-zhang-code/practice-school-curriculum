package io.github.charlie.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import io.github.charlie.base.Result;
import io.github.charlie.convert.ProductConvert;
import io.github.charlie.convert.TrolleyConvert;
import io.github.charlie.model.entity.ProductS;
import io.github.charlie.model.entity.Trolley;
import io.github.charlie.model.form.TrolleyForm;
import io.github.charlie.model.vo.ProductSVO;
import io.github.charlie.service.ProductSService;
import io.github.charlie.service.TrolleyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/9
 * @Description: TODO
 */
@Tag(name = "购物车管理")
@RequestMapping("/api/v1/trolley")
@RestController
@RequiredArgsConstructor
public class TrolleyController {
    private final ProductSService productSService;
    private final TrolleyService service;
    private final TrolleyConvert convert;
    private final ProductConvert productConvert;

    @PostMapping
    public Result<Boolean> create(@RequestBody TrolleyForm form) {
        System.out.println(form);
        Trolley entity = convert.toEntity(form);
        entity.setId(0L);
        boolean save = service.save(entity);
        return Result.status(save);
    }

    @GetMapping("/purchase")
    public Result<Boolean> purchase(@RequestParam Long id) {
        System.out.println(id);
        Trolley t = new Trolley();
        t.setId(id);
        t.setState(1);
        boolean b = service.updateById(t);
        return Result.status(b);
    }

    @DeleteMapping
    public Result<Boolean> delete(@RequestParam Long id) {
        boolean remove = service.removeById(id);
        return Result.status(remove);
    }

    @GetMapping
    public Result<List<ProductSVO>> get() {
        List<ProductSVO> products = service.getProducts(1L, 0L);
        return Result.success(products);
    }
}
