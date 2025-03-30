<script setup lang="ts">
import { router } from '@/router';
import { Get } from '@/utils';

const salesProductsDataRef = ref()
function salesProductsData() {
  Get<any>('/api/v1/product/sales').then((res: any) => {
    console.log(res)
    salesProductsDataRef.value = res
  })
}

onMounted(() => {
  salesProductsData()
})
</script>

<template>
  <div class="block text-center">
    <el-carousel height="250px">
      <el-carousel-item v-for="item in salesProductsDataRef?.data" :key="item.id" @click="router.push(`/product/${item.id}`)">
        <el-image class="h-48 w-full" :src="item?.img"
          fit="cover" />
        <div class="flex gap-2 flex-col h-full p-x-6 p-y-4">
          <div  class="flex gap-2">
            <span>
            {{ item?.name }}
          </span>
            <el-tag>
              {{ item?.specification }}
            </el-tag>
            <el-tag type="danger">
              {{ item?.price }}
            </el-tag>
            <el-tag>
              {{ item?.sales }}
            </el-tag>
          </div>
        </div>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<style scoped>
.demonstration {
  color: var(--el-text-color-secondary);
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 250px;
  margin: 0;
  text-align: center;
}

.el-carousel__item {
  background-color: #ffffff;
}
</style>
