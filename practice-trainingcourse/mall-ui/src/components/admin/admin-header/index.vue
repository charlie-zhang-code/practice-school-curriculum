<script setup lang="ts">
import { router } from '@/router';
import { ElMessage, ElMessageBox } from 'element-plus';


const handleCommand = (command: string | number | object) => {
  if (command === 'logout') {
    ElMessageBox.confirm(
      '确认登出吗？',
      '提示',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
    )
      .then(() => {
        ElMessage({
          type: 'success',
          message: '确认操作',
        })
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '取消操作',
        })
      })
  }
}
</script>

<template>
  <div class="flex items-center justify-between w-full h-full">
    <el-button @click="router.push('/home')">
      返回前台
    </el-button>
    <el-dropdown placement="bottom" @command="handleCommand">
      <el-avatar :size="35" />
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item command="logout">登出</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<style scoped></style>
