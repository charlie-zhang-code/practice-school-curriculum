<script setup lang="ts">
</script>

<template>
  <el-container class="h-screen">
    <el-aside width="200px" class="bg-white">
      <AdminAside />
    </el-aside>
    <el-container>
      <el-header class="flex items-center bg-white">
        <AdminHeader />
      </el-header>
      <el-main>
        <router-view />
      </el-main>
      <el-footer class="bg-white">
        <AdminFooter />
      </el-footer>
    </el-container>
  </el-container>
</template>

<style scoped></style>
