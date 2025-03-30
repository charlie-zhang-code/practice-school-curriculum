package com.mall.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.model.entity.Message;
import com.mall.service.MessageService;
import com.mall.mapper.MessageMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【message】的数据库操作Service实现
* @createDate 2025-01-21 11:17:29
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{


}




