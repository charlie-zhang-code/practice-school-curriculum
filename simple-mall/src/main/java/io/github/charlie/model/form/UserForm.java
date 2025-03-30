package io.github.charlie.model.form;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 用户表单
 */
@Data
public class UserForm implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String avatar;

    private Integer gender;

    private String address;

    private String telephone;
}
