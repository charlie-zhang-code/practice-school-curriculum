<script setup lang="ts">
import { router } from '@/router';
import { Get } from '@/utils';

const axiosDataRef = ref('')
function axiosTest() {
  Get<string>('/test').then((res: string) => {
    console.log(res)
    axiosDataRef.value = res
  })
}
</script>

<template>
  <el-space direction="vertical">
    <h2>测试页面</h2>
    <el-divider />
    <h4>页面访问</h4>
    <el-space direction="vertical">
      <el-button @click="router.push('/home')">首页</el-button>
      <el-button @click="router.push('/search')">搜索详情页</el-button>
      <el-button @click="router.push('/product/1')">商品详情页(id->1)</el-button>
      <el-button @click="router.push('/order/1')">订单详情页(id->1)</el-button>
      <el-button @click="router.push('/comment/1')">评论详情页(id->1)</el-button>
      <el-button @click="router.push('/problem/1')">提问详情页(id->1)</el-button>
      <el-button @click="router.push('/user/1')">用户详情页(id->1)</el-button>
      <el-button @click="router.push('/settings')">用户设置页</el-button>
      <el-button @click="router.push('/trolley')">购物车页</el-button>
    </el-space>
    <el-divider />
    <h4>unocss测试</h4>
    <div class="bg-red">
      红色
    </div>
    <el-divider />
    <h4>Axios测试</h4>
    <div>
      {{ axiosDataRef }}
    </div>
    <el-button @click="axiosTest">
      测试获取数据
    </el-button>
  </el-space>
</template>

<style scoped></style>
