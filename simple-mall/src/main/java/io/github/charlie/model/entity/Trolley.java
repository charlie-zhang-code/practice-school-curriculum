package io.github.charlie.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.charlie.base.BaseEntity;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName sys_trolley
 */
@TableName(value ="sys_trolley")
@Data
public class Trolley extends BaseEntity implements Serializable {
    private Long id;

    private Long productId;

    private Integer state;

    private static final long serialVersionUID = 1L;
}