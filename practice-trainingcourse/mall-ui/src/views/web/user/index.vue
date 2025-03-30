<script setup lang="ts">
// $route.params.id
</script>

<template>
  <div class="flex flex-col gap-4">
    <el-card shadow="never" class="min-h-xl" body-style="padding: 0;">
      <el-image class="w-full h-400px" src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
        fit="cover" />
      <div class="flex flex-col items-center gap-2 mt-6">
        <el-avatar :size="50" />
        <el-text size="large">用户名</el-text>
        <el-text>网龄 5 个月</el-text>
      </div>
    </el-card>
  </div>
</template>

<style scoped></style>
