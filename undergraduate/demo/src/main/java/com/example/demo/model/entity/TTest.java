package com.example.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName t_test
 */
@TableName(value ="t_test")
@Data
public class TTest implements Serializable {
    private Integer id;

    private String name;

    private Integer age;

    private Integer sex;

    private String address;

    private static final long serialVersionUID = 1L;
}