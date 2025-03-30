package com.mall.model.params;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 李茂鑫
 * @Date 2025/1/21 14:55
 * @Descripition
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "添加管理员参数")
public class AdminParams {
    /**
     * 邮箱
     */
    private String email;
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码（已加密）
     */
    private String pwd;
}
