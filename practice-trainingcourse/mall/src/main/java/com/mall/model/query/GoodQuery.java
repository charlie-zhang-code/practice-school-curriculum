package com.mall.model.query;


import com.mall.model.entity.Goods;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 李茂鑫
 * @Date 2025/1/21 11:03
 * @Descripition
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoodQuery extends Goods {
    private Long pageNo = 1L;//当前页码
    private Long pageSize = 10L;//每页显示条数
}