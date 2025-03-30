package com.mall.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户密码
     */
    private String pwd;

    /**
     * 收货人
     */
    private String recipient;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String phone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}