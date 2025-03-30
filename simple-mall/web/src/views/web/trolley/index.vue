<script lang="ts" setup>
import { Delete, Get } from '@/utils';

const productsDataRef = ref()
function productsData() {
  Get<any>(`/api/v1/trolley`).then((res: any) => {
    console.log(res)
    productsDataRef.value = res
  })
}

function deleteData(id: number) {
  console.log(id)
  Delete<any>(`/api/v1/trolley`, {id: id}).then((res: any) => {
    console.log(res)
    productsDataRef.value = res
  })
  // 刷新页面
  window.location.reload()
}

function addData(id: number) {
  console.log(id)
  Get<any>(`/api/v1/trolley/purchase`, {id: id}).then((res: any) => {
    console.log(res)
    productsDataRef.value = res
  })
  // 刷新页面
  window.location.reload()
}

onMounted(() => {
  productsData()
})
</script>

<template>
  <div class="flex flex-col min-h-xl gap-4">
    <el-card shadow="hover" v-for="item in productsDataRef?.data" :key="item.id">
      <div class="flex items-center justify-between">
        <div class="flex">
          <el-image class="w-50 h-25" :src="item?.img" fit="cover" />
          <div class="flex justify-center gap-2 p-x-4 flex-col">
            <div>
              {{ item?.name }}
            </div>
            <div>
              <el-tag>
                {{ item?.specification }}
              </el-tag>
            </div>
            <div>
              <el-tag type="danger">
                {{ item?.price }}
              </el-tag>
            </div>
          </div>
        </div>
        <div class="flex items-center">
          <el-button @click="addData(item?.trolleyId)">
            购买
          </el-button>
          <el-button type="danger" @click="deleteData(item?.trolleyId)">
            删除
          </el-button>
        </div>
      </div>
    </el-card>
  </div>
</template>

<style scoped></style>
