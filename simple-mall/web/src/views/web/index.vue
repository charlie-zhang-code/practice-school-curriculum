<script setup lang="ts">
import { router } from '@/router';
import { Get } from '@/utils';

const newProductsDataRef = ref()
function newProductsData() {
  Get<any>('/api/v1/product/recent').then((res: any) => {
    console.log(res)
    newProductsDataRef.value = res
  })
}

onMounted(() => {
  newProductsData()
})
</script>

<template>
  <div class="flex flex-col min-h-xl gap-4">
    <Recommend />

    <span>
      新上架
    </span>
    <el-space wrap fill :fill-ratio="30">
      <el-card shadow="hover" v-for="item in newProductsDataRef?.data" :key="item.id" body-style="padding: 0;"
        @click="router.push(`/product/${item.id}`)"
      >
        <!-- <el-tag type="danger" class="w-full">
          排行榜Top
        </el-tag> -->
        <el-image class="h-40 w-full" :src="item?.img"
          fit="cover" />
        <div class="flex gap-2 flex-col p-x-6 p-y-4">
          <span>
            {{ item?.name }}
          </span>
          <div>
            <el-tag>
              {{ item?.specification }}
            </el-tag>
          </div>
          <div class="flex gap-2">
            <el-tag type="danger">
              {{ item?.price }}
            </el-tag>
            <el-tag>
              {{ item?.sales }}
            </el-tag>
          </div>
        </div>
      </el-card>
    </el-space>

  </div>
</template>

<style scoped></style>
