<script setup lang="ts">
import { router } from '@/router';
import { Get } from '@/utils';

const keyword = ref('')
const dataRef = ref()
function getSearchData() {
  console.log(keyword.value)

  if (keyword.value !== '') {
    Get<any>(`/api/v1/product/search`, {
    keyword: keyword.value
  }).then((res: any) => {
    console.log(res)
    dataRef.value = res
  })
  }
}
</script>

<template>
  <div class="flex flex-col gap-4">
    <div class="m-auto w-90">
      <el-input placeholder="搜索内容..." v-model="keyword" class="h-10">
        <template #append>
          <el-button @click="getSearchData">
            搜索
          </el-button>
        </template>
      </el-input>
    </div>
    
    <el-space wrap fill :fill-ratio="30">
      <el-card shadow="hover"  v-for="item in dataRef?.data" :key="item.id" 
       @click="router.push(`/product/${item.id}`)"
      body-style="padding: 0;">
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
              {{item?.sales}}
            </el-tag>
          </div>
        </div>
      </el-card>
    </el-space>
    <!-- <el-pagination class="m-auto" background layout="prev, pager, next" :total="1000" /> -->
  </div>
</template>

<style scoped></style>
