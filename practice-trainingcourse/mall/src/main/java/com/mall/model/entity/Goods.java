package com.mall.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 
 * @TableName goods
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "商品实体类")
@Builder
@EqualsAndHashCode(callSuper = false)
public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 商品编号
     */

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 商品创建时间
     */

   @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;



    /**
     * 商品更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 商品创建人
     */
    private String createPeople;

    /**
     * 商品修改人
     */
    private String updatePeople;

    /**
     * 商品图片
     */
    private String img;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 商品所属类目id
     */
    private Integer typeid;

    /**
     * 商品总库存
     */
    private Integer stockNum;

    /**
     * 商品描述
     */
    private String goodsDesc;


    }




