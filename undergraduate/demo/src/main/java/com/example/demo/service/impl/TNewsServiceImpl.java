package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.model.entity.TNews;
import com.example.demo.service.TNewsService;
import com.example.demo.mapper.TNewsMapper;
import org.springframework.stereotype.Service;

/**
* @author Charlie
* @description 针对表【t_news】的数据库操作Service实现
* @createDate 2025-04-18 17:39:21
*/
@Service
public class TNewsServiceImpl extends ServiceImpl<TNewsMapper, TNews>
    implements TNewsService{

}




