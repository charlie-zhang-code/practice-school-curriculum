<script setup lang="ts">
import { router } from '@/router';
</script>

<template>
  <el-menu class="w-200px h-full">
    <el-menu-item index="0" @click="router.push('/admin/index')">
      <el-icon><House /></el-icon>
      <span>首页</span>
    </el-menu-item>
    <el-menu-item index="1" @click="router.push('/admin/user')">
      <el-icon><User /></el-icon>
      <span>用户管理</span>
    </el-menu-item>
    <el-menu-item index="2" @click="router.push('/admin/product')">
      <el-icon><Handbag /></el-icon>
      <span>商品管理</span>
    </el-menu-item>
    <!-- <el-menu-item index="3" @click="router.push('/admin/category')">
      <el-icon><CollectionTag /></el-icon>
      <span>类别管理</span>
    </el-menu-item>
    <el-menu-item index="4" @click="router.push('/admin/specification')">
      <el-icon><PriceTag /></el-icon>
      <span>规格管理</span>
    </el-menu-item>
    <el-menu-item index="5" @click="router.push('/admin/comment')">
      <el-icon><ChatDotSquare /></el-icon>
      <span>评论管理</span>
    </el-menu-item>
    <el-menu-item index="6" @click="router.push('/admin/problem')">
      <el-icon><ChatLineRound /></el-icon>
      <span>提问管理</span>
    </el-menu-item>
    <el-menu-item index="7" @click="router.push('/admin/order')">
      <el-icon><Tickets /></el-icon>
      <span>订单管理</span>
    </el-menu-item> -->
  </el-menu>
</template>

<style scoped></style>
