package com.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.model.entity.Comment;
import com.mall.service.CommentService;
import com.mall.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author Charlie
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2025-01-20 10:21:28
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




