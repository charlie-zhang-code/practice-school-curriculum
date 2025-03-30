<script setup lang="ts">
import { router } from '@/router'
function goToLogin() {
  router.push('/login')
}
function goToRegister() {
  router.push('/register')
}
function register() {
  // 注册逻辑
}
</script>

<template>
  <div class="container">
    <h1>Simple Mall</h1>
    <el-form >
      <div class="nav">
        <el-link href="#" @click="goToLogin" class="active">登录</el-link>
        <el-link href="#" @click="goToRegister">注册</el-link>
      </div>
      <div class="nac">
        <el-form-item>
          <el-input  placeholder="请输入用户名" required></el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="password"  placeholder="请输入密码" required></el-input>
        </el-form-item>
      </div>
      <el-button type="primary" native-type="submit" class="submit-btn">登录</el-button>
    </el-form>
  </div>
</template>

<style scoped>
.container {
  width: 300px;
  margin: 100px auto;
  padding: 20px;
  background-color: #f9f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.container h1 {
  color: #409eff;
  margin-bottom: 20px;
}

.nac {
  width: 100%;
  padding: 15px;
}

.nav {
  margin-bottom: 20px;
}

.nav .el-link {
  color: #666;
  text-decoration: none;
  margin: 0 10px;
}

.nav .active {
  color: #409eff;
  font-weight: bold;
}

.el-form-item {
  margin-bottom: 15px;
}

.el-input,
.el-select {
  width: 90%;
}

.submit-btn {
  width: 100%;
  padding: 10px;
  background-color: #409eff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.submit-btn:hover {
  background-color: #2a8edb;
}
</style>