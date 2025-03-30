package io.github.charlie.model.vo;

import io.github.charlie.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 用户脱敏返回
 */
@Data
public class UserVO  extends BaseEntity implements Serializable {
    private Long id;

    private String username;

    private String nickname;

    private String avatar;

    private Integer gender;

    private String address;

    private String telephone;

    private static final long serialVersionUID = 1L;
}
