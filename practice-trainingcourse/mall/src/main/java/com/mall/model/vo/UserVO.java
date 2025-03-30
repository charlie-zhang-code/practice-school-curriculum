package com.mall.model.vo;

import lombok.Data;

/**
 * @Author: Charlie Zhang(Github: @charlie-zhang-code)
 * @Date: 2025/1/21
 * @Description: 用户脱敏实体类
 */
@Data
public class UserVO {
    private Integer id;

    private String email;

    private String nickname;

    private String recipient;

    private String address;

    private String phone;
}
