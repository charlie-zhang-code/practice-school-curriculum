package com.mall.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName comment
 */
@TableName(value ="comment")
public class Comment implements Serializable {
    /**
     * 评论编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 评论商品的id
     */
    private Integer goodsid;

    /**
     * 评论商品的规格名称
     */
    private String specname;

    /**
     * 评论的内容
     */
    private String comment;

    /**
     * 评论的时间
     */
    private Date commenttime;

    /**
     * 用户的id
     */
    private Integer userid;

    /**
     * 用户评分
     */
    private Double score;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 评论编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 评论编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 评论商品的id
     */
    public Integer getGoodsid() {
        return goodsid;
    }

    /**
     * 评论商品的id
     */
    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    /**
     * 评论商品的规格名称
     */
    public String getSpecname() {
        return specname;
    }

    /**
     * 评论商品的规格名称
     */
    public void setSpecname(String specname) {
        this.specname = specname;
    }

    /**
     * 评论的内容
     */
    public String getComment() {
        return comment;
    }

    /**
     * 评论的内容
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 评论的时间
     */
    public Date getCommenttime() {
        return commenttime;
    }

    /**
     * 评论的时间
     */
    public void setCommenttime(Date commenttime) {
        this.commenttime = commenttime;
    }

    /**
     * 用户的id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 用户的id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 用户评分
     */
    public Double getScore() {
        return score;
    }

    /**
     * 用户评分
     */
    public void setScore(Double score) {
        this.score = score;
    }
}