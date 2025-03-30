package io.github.charlie.base;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 基础查询对象
 */
@Data
public class BaseQuery implements Serializable {
    private int pageNum; // 当前页码
    private int pageSize; // 每页条数

    private String keyword; // 关键词

    @Serial
    private static final long serialVersionUID = 1L;
}
