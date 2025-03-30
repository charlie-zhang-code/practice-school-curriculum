package com.mall.model.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 李茂鑫
 * @Date 2025/1/20 10:05
 * @Descripition 管理员实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "用户实体类")
@TableName(value ="admin")
@EqualsAndHashCode(callSuper = false)
public class Admin implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 管理员编号
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建人
     */
    private String createPeople;

    /**
     * 修改人
     */
    private String updatePeople;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码（已加密）
     */
    private String pwd;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}

