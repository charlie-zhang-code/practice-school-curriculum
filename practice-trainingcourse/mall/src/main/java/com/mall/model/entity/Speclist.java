package com.mall.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName speclist
 */
@TableName(value ="speclist")
@Data
public class Speclist implements Serializable {
    /**
     * 规格编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 规格名称
     */
    private String specName;

    /**
     * 规格库存
     */
    private String stockNum;

    /**
     * 规格价格
     */
    private String unitPrice;

    /**
     * 商品id
     */
    private Integer goodsId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}