package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.model.entity.TNews;
import com.example.demo.model.entity.TTest;
import com.example.demo.service.TNewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Charlie Zhang
 * @version v1.0
 * @date 18/04/2025
 * @description news表访问控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final TNewsService tNewsService;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        // 测试
//        List<Map<String, Object>> newsList = new ArrayList<>();
//        Map<String, Object> news = new HashMap<>();
//        news.put("id", 1);
//        news.put("title", "Sample Title");
//        news.put("img", "https://file.psd.cn/p/stock/20221014/f1lj5pww4yo.jpg");
//        news.put("cTime", "2025-04-18");
//        newsList.add(news);
        List<TNews> list = tNewsService.list();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/detail")
    public ResponseEntity<?> detail(@RequestParam("id") String id) {
        // 测试
//        List<Map<String, Object>> newsList = new ArrayList<>();
//        Map<String, Object> news = new HashMap<>();
//        news.put("id", 1);
//        news.put("title", "Sample Title");
//        news.put("img", "https://file.psd.cn/p/stock/20221014/f1lj5pww4yo.jpg");
//        news.put("cTime", "2025-04-18");
//        news.put("content", "Sample Content");
//        newsList.add(news);
        List<TNews> list = tNewsService.list(new LambdaQueryWrapper<TNews>()
                .eq(TNews::getId, id)
        );
        return ResponseEntity.ok(list);
    }

}
