package com.mall.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 
 * @TableName orders
 */
@TableName(value ="orders")
public class Orders implements Serializable {
    /**
     * 订单编号
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 商品规格id
     */
    private Integer goodsDetailId;

    /**
     * 商品名称
     */
    private String goods;

    /**
     * 商品规格名称
     */
    private String spec;

    /**
     * 商品库存
     */
    private Integer goodsNum;

    /**
     * 商品价格
     */
    private Double amount;

    /**
     * 订单状态，0未付款，未发货1，已发货2，确认收货3
     */
    private Integer stateId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 收货人
     */
    private String name;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 付款金额
     */
    private Double unitPrice;

    /**
     * 商品图片
     */
    private String img;

    /**
     * 商品评论状态，1未评论，0已评论
     */
    private Integer comment;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 订单生成时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 订单更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String createPeople;

    /**
     * 订单修改人
     */
    private String updatePeople;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 订单编号
     */
    public void setId(Integer id) {
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
     * 商品规格id
     */
    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    /**
     * 商品规格id
     */
    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    /**
     * 商品名称
     */
    public String getGoods() {
        return goods;
    }

    /**
     * 商品名称
     */
    public void setGoods(String goods) {
        this.goods = goods;
    }

    /**
     * 商品规格名称
     */
    public String getSpec() {
        return spec;
    }

    /**
     * 商品规格名称
     */
    public void setSpec(String spec) {
        this.spec = spec;
    }

    /**
     * 商品库存
     */
    public Integer getGoodsNum() {
        return goodsNum;
    }

    /**
     * 商品库存
     */
    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    /**
     * 商品价格
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 商品价格
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * 订单状态，0未付款，未发货1，已发货2，确认收货3
     */
    public Integer getStateId() {
        return stateId;
    }

    /**
     * 订单状态，0未付款，未发货1，已发货2，确认收货3
     */
    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    /**
     * 用户昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 用户昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 收货人
     */
    public String getName() {
        return name;
    }

    /**
     * 收货人
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 收货地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 收货地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 付款金额
     */
    public Double getUnitPrice() {
        return unitPrice;
    }

    /**
     * 付款金额
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 商品图片
     */
    public String getImg() {
        return img;
    }

    /**
     * 商品图片
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 商品评论状态，1未评论，0已评论
     */
    public Integer getComment() {
        return comment;
    }

    /**
     * 商品评论状态，1未评论，0已评论
     */
    public void setComment(Integer comment) {
        this.comment = comment;
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
     * 订单生成时间
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 订单生成时间
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 订单更新时间
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 订单更新时间
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 创建人
     */
    public String getCreatePeople() {
        return createPeople;
    }

    /**
     * 创建人
     */
    public void setCreatePeople(String createPeople) {
        this.createPeople = createPeople;
    }

    /**
     * 订单修改人
     */
    public String getUpdatePeople() {
        return updatePeople;
    }

    /**
     * 订单修改人
     */
    public void setUpdatePeople(String updatePeople) {
        this.updatePeople = updatePeople;
    }
}