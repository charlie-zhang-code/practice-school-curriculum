package io.github.charlie.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.charlie.base.BaseEntity;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class User extends BaseEntity implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String avatar;

    private Integer gender;

    private String address;

    private String telephone;

    private static final long serialVersionUID = 1L;
}