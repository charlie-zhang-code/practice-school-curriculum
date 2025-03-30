package com.mall.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName type
 */
@TableName(value ="type")
@Data
public class Type implements Serializable {
    /**
     * 商品类目编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 类目名称
     */
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}