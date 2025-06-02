package com.example.demo.controller;

import com.example.demo.model.entity.TTest;
import com.example.demo.service.TTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Charlie Zhang
 * @version v1.0
 * @date 17/04/2025
 * @description DB测试接口
 */
@Slf4j
@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {
    private final TTestService testService;

    @GetMapping("/one/{id}")
    public TTest one(@PathVariable String id) {
        return testService.getById(id);
    }

    @GetMapping("/list")
    public List<TTest> list() {
        return testService.list();
    }

}
