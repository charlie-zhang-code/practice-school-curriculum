package io.github.charlie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.charlie.model.entity.Comment;
import io.github.charlie.service.CommentService;
import io.github.charlie.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author Charlie
* @description 针对表【sys_comment(评论表)】的数据库操作Service实现
* @createDate 2025-02-05 16:51:40
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




