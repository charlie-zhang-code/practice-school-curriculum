package io.github.charlie.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.charlie.base.BaseEntity;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName sys_comment
 */
@TableName(value ="sys_comment")
@Data
public class Comment extends BaseEntity implements Serializable {
    private Long id;

    private Long orderId;

    private Long productId;

    private Long specificationId;

    private String content;

    private Integer score;

    private static final long serialVersionUID = 1L;
}