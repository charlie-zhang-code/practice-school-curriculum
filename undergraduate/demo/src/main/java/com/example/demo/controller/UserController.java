package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.model.entity.TUser;
import com.example.demo.model.form.LoginForm;
import com.example.demo.model.form.RegisterForm;
import com.example.demo.model.form.WxLoginDTO;
import com.example.demo.service.TUserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Charlie Zhang
 * @version v1.0
 * @date 26/04/2025
 * @description TODO
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final TUserService tUserService;

    /**
     * 登陆
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm form, HttpSession session) {
        // 判断数据库中是否存在该用户
        boolean exists = tUserService.exists(new LambdaQueryWrapper<TUser>()
                .eq(TUser::getUsername, form.getUsername())
        );
        if (!exists) {
            // 不存在
            throw new RuntimeException("用户不存在");
        }

        // 判断密码是否正确
        boolean passwordCorrect = tUserService.exists(new LambdaQueryWrapper<TUser>()
                .eq(TUser::getUsername, form.getUsername())
                .eq(TUser::getPassword, form.getPassword())
        );
        if (!passwordCorrect) {
            // 密码错误
            throw new RuntimeException("密码错误");
        }

        // 登录成功，将用户信息存入session
        TUser user = tUserService.getOne(new LambdaQueryWrapper<TUser>()
                .eq(TUser::getUsername, form.getUsername())
                .eq(TUser::getPassword, form.getPassword())
        );
        session.setAttribute("currentUser", user);

        TUser currentUser = (TUser) session.getAttribute("currentUser");
        if (currentUser != null) {
            return ResponseEntity.ok(currentUser);
        } else {
            return ResponseEntity.ok("未登录");
        }
    }

    /**
     * 检查用户是否登录
     */
    @GetMapping("/check-login")
    public ResponseEntity<?> checkLogin(HttpSession session) {
        TUser currentUser = (TUser) session.getAttribute("currentUser");
        if (currentUser != null) {
            return ResponseEntity.ok("已登录，用户ID: " + currentUser.getId());
        } else {
            return ResponseEntity.ok("未登录");
        }
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.removeAttribute("currentUser");
        return ResponseEntity.ok("登出成功");
    }

    /**
     * 微信登录
     */
    @PostMapping("/wxlogin")
    public ResponseEntity<?> wechatLogin(@RequestBody WxLoginDTO wxLoginDTO, HttpSession session) {
        try {
            // 根据操作标志处理不同逻辑
            if ("getOpenid".equals(wxLoginDTO.getOperFlag())) {
                // 这里应该调用微信API服务获取openid和session_key
                // 实际开发中需要替换为你的微信小程序appid和secret
                String appid = "";
                String secret = "";
                String code = wxLoginDTO.getCode();

                // 构建请求URL
                String url = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                        appid, secret, code);

                // 发送HTTP请求到微信服务器
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

                // 解析响应
                if (response.getStatusCode().is2xxSuccessful()) {
                    String responseBody = response.getBody();
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode jsonNode = mapper.readTree(responseBody);

                    // 检查微信返回的错误
                    if (jsonNode.has("errcode")) {
                        int errcode = jsonNode.get("errcode").asInt();
                        String errmsg = jsonNode.get("errmsg").asText();
                        log.error("微信登录失败: {} - {}", errcode, errmsg);
                        return ResponseEntity.badRequest().body("微信登录失败: " + errmsg);
                    }

                    // 获取openid和session_key
                    String openid = jsonNode.get("openid").asText();
                    String sessionKey = jsonNode.get("session_key").asText();

                    // 检查数据库中是否已存在该微信用户
                    LambdaQueryWrapper<TUser> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.eq(TUser::getOpenid, openid);
                    TUser user = tUserService.getOne(queryWrapper);

                    if (user == null) {
                        // 新用户，自动注册
                        user = new TUser();
                        user.setOpenid(openid);
                        user.setSessionKey(sessionKey);
                        user.setNickname("微信用户_" + System.currentTimeMillis() % 10000);
                        user.setAvatar("https://www.keaitupian.cn/cjpic/frombd/1/253/728328963/1751682274.jpg");

                        // 设置默认用户名
                        user.setUsername("wx_" + openid.substring(0, 8)); // 使用openid前8位作为用户名
                        user.setPassword("123456"); // 设置默认密码

                        tUserService.save(user);
                    } else {
                        // 更新session_key
                        user.setSessionKey(sessionKey);
                        tUserService.updateById(user);
                    }

                    // 登录成功，将用户信息存入session
                    session.setAttribute("currentUser", user);

                    // 返回用户信息和token（实际项目中可能需要生成JWT token）
                    Map<String, Object> result = new HashMap<>();
                    result.put("user", user);
                    result.put("sessionId", session.getId());

                    return ResponseEntity.ok(result);
                } else {
                    return ResponseEntity.badRequest().body("获取微信openid失败");
                }
            } else {
                return ResponseEntity.badRequest().body("无效的操作标志");
            }
        } catch (Exception e) {
            log.error("微信登录异常", e);
            return ResponseEntity.internalServerError().body("微信登录异常: " + e.getMessage());
        }
    }

    /**
     * 注册
     */
    @Transactional
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterForm form) {
        // 判断用户名是否已存在
        boolean exists = tUserService.exists(new LambdaQueryWrapper<TUser>()
                .eq(TUser::getUsername, form.getUsername())
        );
        if (exists) {
            // 已存在
            throw new RuntimeException("用户名已存在");
        }

        // 创建用户
        TUser user = new TUser();
        user.setAvatar("https://www.keaitupian.cn/cjpic/frombd/1/253/728328963/1751682274.jpg");
        user.setNickname("神秘用户");
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());
        boolean save = tUserService.save(user);
        if (!save) {
            // 保存失败
            throw new RuntimeException("注册失败");
        }

        return ResponseEntity.ok("注册成功");
    }
}
