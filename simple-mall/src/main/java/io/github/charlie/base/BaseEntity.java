package io.github.charlie.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 基础实体类
 */
@Data
public class BaseEntity implements Serializable {
    private Integer status;

    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
