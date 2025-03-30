package com.mall.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName message
 */
@TableName(value ="message")
public class Message implements Serializable {
    /**
     * 提问编号
     */
    @TableId(type=IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 提问内容
     */
    private String content;

    /**
     * 提问状态，0表示已回复1表示未回复
     */
    private Integer state;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 提问时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createtime;

    /**
     * 回复时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date replytime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 提问编号
     */
    public String getId() {
        return id;
    }

    /**
     * 提问编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 商品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 商品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 提问内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 提问内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 提问状态，0表示已回复1表示未回复
     */
    public Integer getState() {
        return state;
    }

    /**
     * 提问状态，0表示已回复1表示未回复
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 回复内容
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * 回复内容
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    /**
     * 提问时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 提问时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 回复时间
     */
    public Date getReplytime() {
        return replytime;
    }

    /**
     * 回复时间
     */
    public void setReplytime(Date replytime) {
        this.replytime = replytime;
    }
}