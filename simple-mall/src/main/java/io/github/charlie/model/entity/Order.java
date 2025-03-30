package io.github.charlie.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.charlie.base.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @TableName sys_order
 */
@TableName(value ="sys_order")
@Data
public class Order extends BaseEntity implements Serializable {
    private Long id;

    private Long productId;

    private Long specificationId;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private Integer state;

    private Integer paymentMethod;

    private Date paymentTime;

    private Date deliveryTime;

    private Date receiveTime;

    private Integer commentState;

    private Integer scoreState;

    private static final long serialVersionUID = 1L;
}