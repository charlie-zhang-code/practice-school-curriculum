<script setup lang="ts">
import { Get } from '@/utils';

const productDataRef = ref()
function productData() {
  Get<any>(`/api/v1/product/list`).then((res: any) => {
    console.log(res)
    productDataRef.value = res
  })
}

onMounted(() => {
  productData()
})
</script>

<template>
  <div class="flex flex-col min-h-xl gap-2">
    <el-card shadow="hover" v-for="item in productDataRef?.data" :key="item.id">
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
