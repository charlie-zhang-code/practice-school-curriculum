package com.example.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName t_news
 */
@TableName(value ="t_news")
@Data
public class TNews implements Serializable {
    private Integer id;

    private String title;

    private String img;

    private Date ctime;

    private String content;

    private static final long serialVersionUID = 1L;
}