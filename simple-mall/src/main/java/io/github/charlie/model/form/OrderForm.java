package io.github.charlie.model.form;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 订单表单
 */
@Data
public class OrderForm implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

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
}
