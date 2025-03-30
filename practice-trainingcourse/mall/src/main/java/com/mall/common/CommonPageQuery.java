package com.mall.common;

import lombok.Data;

/**
 * @Author: Charlie Zhang(Github: @charlie-zhang-code)
 * @Date: 2025/1/20
 * @Description: 基础查询
 */
@Data
public class CommonPageQuery {
    private Integer pageNum;
    private Integer pageSize;
    private String keyword;
}
