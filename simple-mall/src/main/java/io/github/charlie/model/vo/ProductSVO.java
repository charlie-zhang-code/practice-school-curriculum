package io.github.charlie.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.charlie.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductSVO extends BaseEntity implements Serializable {
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

    private Long trolleyId;

    private static final long serialVersionUID = 1L;
}