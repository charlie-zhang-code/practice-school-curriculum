package com.example.demo.model.form;

import lombok.Data;

/**
 * 微信登录数据传输对象
 */
@Data
public class WxLoginDTO {
    /**
     * 微信登录凭证 code
     */
    private String code;
    
    /**
     * 操作标志
     * 目前支持的值: "getOpenid" - 获取openid
     */
    private String operFlag;
    
    /**
     * 以下字段可选，用于用户信息解密或完善用户信息
     */
    private String encryptedData;  // 包括敏感数据在内的完整用户信息的加密数据
    private String iv;             // 加密算法的初始向量
    private String rawData;        // 不包括敏感信息的原始数据字符串，用于计算签名
    private String signature;      // 使用 sha1( rawData + sessionkey ) 得到字符串，用于校验用户信息
}