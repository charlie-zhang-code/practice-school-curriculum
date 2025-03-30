package io.github.charlie.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.charlie.base.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @TableName sys_product_s
 */
@TableName(value ="sys_product_s")
@Data
public class ProductS extends BaseEntity implements Serializable {
    private Long id;

    private String brand;

    private String img;

    private String name;

    private Integer sales;

    private String description;

    private String category;

    private String specification;

    private Integer stock;

    private BigDecimal price;

    private static final long serialVersionUID = 1L;
}