package com.mall.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author 李茂鑫
 * @Date 2025/1/21 16:08
 * @Descripition  自动填充createTime和updateTime两个字段
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateTime",LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",LocalDateTime.now(), metaObject);
        this.setFieldValByName("createTime",LocalDateTime.now(), metaObject);
    }
}
