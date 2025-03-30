<script setup lang="ts">
const options = [
  {
    value: '0',
    label: '未知',
  },
  {
    value: '1',
    label: '男',
  },
  {
    value: '2',
    label: '女',
  }
]
</script>

<template>
  <div class="flex flex-col gap-4">
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>用户信息</span>
        </div>
      </template>
      <div class="flex flex-col max-w-80">
        <el-form label-width="auto">
          <el-form-item label="头像">
            <el-upload class="avatar-uploader" :show-file-list="false">
              <el-avatar :size="50" />
            </el-upload>
          </el-form-item>
          <el-form-item label="用户ID">
            <el-input autocomplete="off" disabled/>
          </el-form-item>
          <el-form-item label="用户名">
            <el-input autocomplete="off" />
          </el-form-item>
          <el-form-item label="性别">
            <el-select placeholder="Select">
              <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
            </el-select>
          </el-form-item>
          <el-form-item label="手机号">
            <el-input autocomplete="off" />
          </el-form-item>
          <el-form-item label="地址">
            <el-input type="textarea" autocomplete="off" />
          </el-form-item>
        </el-form>
      </div>
      <template #footer>
        <div class="flex justify-end items-center">
          <el-button type="primary">
            保存
          </el-button>
        </div>
      </template>
    </el-card>

    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>安全设置</span>
        </div>
      </template>
      <div class="flex flex-col max-w-80">
        <el-form label-width="auto">
          <el-form-item label="新密码">
            <el-input type="password" autocomplete="off" />
          </el-form-item>
        </el-form>
      </div>
      <template #footer>
        <div class="flex justify-end items-center">
          <el-button type="primary">
            保存
          </el-button>
        </div>
      </template>
    </el-card>

    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>账户操作</span>
        </div>
      </template>
      <div class="flex flex-col max-w-80">
        <el-form label-width="auto">
          <el-form-item label="注销账户">
            <el-space>
              <el-input autocomplete="off" placeholder="输入“确认”执行注销" />
              <el-button type="danger">
                注销
              </el-button>
            </el-space>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<style scoped></style>
