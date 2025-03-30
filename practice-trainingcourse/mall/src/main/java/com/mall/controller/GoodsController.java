package com.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.CommonResult;
import com.mall.model.entity.Goods;
import com.mall.model.query.GoodQuery;
import com.mall.service.GoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * @author 李茂鑫
 * @Date 2025/1/21 9:53
 * @Descripition
 */
@Tag(name = "商品管理器")
@RequestMapping("/good")
@RestController
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    /**
     * 添加商品
     * @param goods 商品对象
     * @return 添加结果
     */
    @PostMapping("/add")
    @CrossOrigin
    @Operation(summary = "添加商品")
    public CommonResult createGoods(@RequestBody Goods goods) {
        // TODO 【优化方向】03 和admincontroller一样，参数过多，createPeople、updatePeople、createTime、updateTime字段是发请求多余的
        try {
            // 校验商品名称和价格
            if (goods.getName() == null || goods.getPrice() == null) {
                return CommonResult.error("商品名称和价格为必填项");
            }


            if (Double.compare(goods.getPrice(), 0.0) <= 0) {
                return CommonResult.error("商品价格必须大于0");
            }

            boolean createdGoods = goodsService.save(goods);
            if (createdGoods) {
                return CommonResult.success("添加成功");
            } else {
                return CommonResult.error("添加失败");
            }
        } catch (Exception e) {

            return CommonResult.error("系统错误，请稍后再试");
        }
    }

    /**
     * 更新商品
     * @param goods 商品对象
     * @return 更新结果
     */
    @PutMapping("/update")
    @CrossOrigin
    @Operation(summary = "更新商品")
    public CommonResult updateGoods(@RequestBody Goods goods) {
        // TODO 【优化方向】 04 参数过多
        try {

            if (goods.getId() == null) {
                return CommonResult.error("商品ID不能为空");
            }

            boolean updatedGoods = goodsService.updateById(goods);
            if (updatedGoods) {
                return CommonResult.success("更新成功");
            } else {
                return CommonResult.error("更新失败");
            }
        } catch (Exception e) {

            return CommonResult.error("系统错误，请稍后再试");
        }
    }

    /**
     * 删除商品
     * @param id 商品ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @CrossOrigin
    @Operation(summary = "删除商品")
    public CommonResult deleteGoods(@PathVariable Serializable id) {
        try {
            boolean deletedGoods = goodsService.removeById(id);
            if (deletedGoods) {
                return CommonResult.success("删除成功");
            } else {
                return CommonResult.error("删除失败");
            }
        } catch (Exception e) {

            return CommonResult.error("系统错误，请稍后再试");
        }
    }

    /**
     * 查询商品
     * @param id 商品ID
     * @return 商品信息
     */
    @GetMapping("/{id}")
    @CrossOrigin
    @Operation(summary = "查询商品")
    public CommonResult getGoods(@PathVariable Serializable id) {
        try {
            Goods goods = goodsService.getById(id);
            if (goods != null) {
                return CommonResult.success(goods);
            } else {
                return CommonResult.error("商品未找到");
            }
        } catch (Exception e) {

            return CommonResult.error("系统错误，请稍后再试");
        }
    }

    /**
     * 分页查询商品
     * @param GoodQuery 查询参数
     * @return 商品列表
     */
    @GetMapping("/listAll")
    @CrossOrigin
    @Operation(summary = "分页查询商品")
    public CommonResult listGoods(GoodQuery GoodQuery) {
        try {
            // 创建分页对象
            Page<Goods> page = new Page<>(GoodQuery.getPageNo(), GoodQuery.getPageSize());

            QueryWrapper<Goods> wrapper = new QueryWrapper<>();

            // wrapper.eq("column_name", goodQueryVo.getSomeField());

            // 调用分页查询方法
            IPage<Goods> goodsPage = goodsService.page(page, wrapper);
            return CommonResult.success(goodsPage);
        } catch (Exception e) {
            return CommonResult.error("系统错误，请稍后再试");
        }
    }
}











