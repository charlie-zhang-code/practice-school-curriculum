package com.mall.model.query;

import com.mall.model.entity.Orders;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 李茂鑫
 * @Date 2025/1/21 14:41
 * @Descripition
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderQuery extends Orders {
    private Long pageNo = 1L;//当前页码
    private Long pageSize = 10L;//每页显示条数
}
