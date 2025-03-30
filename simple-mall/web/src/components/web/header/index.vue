<script setup lang="ts">
import { router } from '@/router';
import { ElMessage, ElMessageBox } from 'element-plus';

const handleCommand = (command: string | number | object) => {
    if (command === 'user') {
        router.push('/user/1')
    } else if (command === 'settings') {
        router.push('/settings')
    } else if (command === 'admin') {
        router.push('/admin')
    } else if (command === 'logout') {
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
    <div class="w-full flex justify-between items-center">
        <div class="flex flex-1 items-center gap-2">
            <!-- <el-image class="w-10 h-10" fit="cover" /> -->
            <span class="w-30 text-slate-700">
                Simple Mall
            </span>
            <div class="w-full flex items-center gap-4">
                <el-button link @click="router.push('/home')">
                    首页
                </el-button>
                <!-- <el-button link type="warning" @click="router.push('/test')">
                    测试页
                </el-button> -->
            </div>
        </div>
        <div class="flex items-center gap-4">
            <!-- <el-input placeholder="搜索内容..."> -->
                <!-- <template #append> -->
                    <el-button @click="router.push('/search')">
                        搜索
                    </el-button>
                <!-- </template> -->
            <!-- </el-input> -->
            <!-- 登录状态才有 -->
            <div class="flex items-center gap-4">
                <el-button link @click="router.push('/trolley')">
                    购物车
                </el-button>
                <el-dropdown placement="bottom" @command="handleCommand">
                    <el-avatar :size="35" />
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item command="user">用户中心</el-dropdown-item>
                            <el-dropdown-item command="settings">设置中心</el-dropdown-item>
                            <!-- 管理员才有 -->
                            <el-dropdown-item command="admin">管理中心</el-dropdown-item>

                            <el-dropdown-item command="logout">登出</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </div>
            <div class="flex">
                <el-button link @click="router.push('/login')">登录</el-button>
                <el-button link @click="router.push('/register')">注册</el-button>
            </div>
        </div>

    </div>

</template>

<style scoped></style>
