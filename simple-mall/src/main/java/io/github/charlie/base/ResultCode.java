package io.github.charlie.base;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 结果码枚举
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ResultCode implements IResultCode, Serializable {
    SUCCESS("200", "成功"),
    FAIL("500", "失败"),
    VALIDATE_FAILED("400", "参数校验失败"),
    UNAUTHORIZED("401", "未授权"),
    FORBIDDEN("403", "禁止访问"),
    ;

    private String code;

    private String message;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
