package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.model.entity.TTest;
import com.example.demo.service.TTestService;
import com.example.demo.mapper.TTestMapper;
import org.springframework.stereotype.Service;

/**
* @author Charlie
* @description 针对表【t_test】的数据库操作Service实现
* @createDate 2025-04-17 08:54:13
*/
@Service
public class TTestServiceImpl extends ServiceImpl<TTestMapper, TTest>
    implements TTestService{

}




