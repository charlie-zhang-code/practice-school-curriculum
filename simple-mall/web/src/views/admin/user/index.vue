<script setup lang="ts">
import { Get } from '@/utils';

const userDataRef = ref()
function userData() {
  Get<any>(`/api/v1/user/list`).then((res: any) => {
    console.log(res)
    userDataRef.value = res
  })
}

onMounted(() => {
  userData()
})

</script>

<template>
  <div class="flex flex-col min-h-xl gap-2">
    <el-card shadow="hover" v-for="item in userDataRef?.data" :key="item.id">
      <div class="flex items-center justify-between">
        <div class="flex">
          <el-image class="w-50 h-25" :src="item?.img" fit="cover" />
          <div class="flex justify-center gap-2 p-x-4 flex-col">
            <div>
              {{ item?.nickname }}
            </div>
            <div>
              <el-tag>
                {{ item?.username }}
              </el-tag>
            </div>
            <div>
              <el-tag type="danger">
                {{ item?.gender }}
              </el-tag>
            </div>
          </div>
        </div>
        <div class="flex items-center">
          <el-button >
            编辑
          </el-button>
          <el-button type="danger" >
            删除
          </el-button>
        </div>
      </div>
    </el-card>
  </div>
</template>

<style scoped>

</style>
