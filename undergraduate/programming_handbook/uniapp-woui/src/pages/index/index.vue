<script setup lang="ts">
  import { ref } from 'vue'

  import { request } from '@/utils'

  const swiperList = ref([])  // 用于存储处理后的图片数组
  async function getSwiperData() {
    const response = await request.Get('/api/banners/')
    const listData = response.data
    const imagesArray = listData.map(item => item.image)
    swiperList.value = imagesArray
  }

  const recentArtcles = ref([])
  async function getRecentArtclesData() {
    const response = await request.Get('/api/articles/recent/')
    recentArtcles.value = response.data
  }

  onMounted(() => {
    getSwiperData()
    getRecentArtclesData()
  })

  const current = ref<number>(0)
  const features = ref([
    { id: 1, icon: 'code', name: '代码片段', color: '#5e72e4' },
    { id: 2, icon: 'computer', name: '命令速查', color: '#11cdef' },
    { id: 3, icon: 'tools', name: '开发工具', color: '#2dce89' },
    { id: 4, icon: 'help', name: '常见问题', color: '#fb6340' }
  ])

  function goMorePage() { uni.navigateTo({ url: '/pages/articles/index' }) }
  function goCodePage() { uni.switchTab({ url: '/pages/code/index' }) }
  function goCommandPage() { uni.switchTab({ url: '/pages/command/index' }) }
  function goToolsPage() { uni.switchTab({ url: '/pages/tools/index' }) }
  function goHelpPage() { uni.switchTab({ url: '/pages/help/index' }) }
</script>

<template>
  <view>
    <wd-swiper :list="swiperList" autoplay v-model:current="current"></wd-swiper>

    <wd-grid square >
      <wd-grid-item class="ie1" icon="code" @click="goCodePage" text="代码片段" />
      <wd-grid-item class="ie2" icon="computer" @click="goCommandPage" text="命令速查" />
      <wd-grid-item class="ie3" icon="tools" @click="goToolsPage" text="开发工具" />
      <!-- <wd-grid-item class="ie4" icon="help" @click="goHelpPage" text="常见问题" /> -->
    </wd-grid>

    <wd-cell-group border title="最近更新" use-slot>
      <template #title>
        最近文章
      </template>
      <template #value>
        <wd-button size="small" @click="goMorePage">更多</wd-button>
      </template>
      <wd-cell :title="item.title" :label="item.time_since_published" is-link v-for="item in recentArtcles"
        :key="item.id" :to="`/pages/article/index?id=${item.id}`" />
    </wd-cell-group>
  </view>
</template>

<style scoped lang="scss">
:deep(.ie1 .wd-grid-item__content) {
  background-color: #5e72e4;
  color: white;
}
:deep(.ie2 .wd-grid-item__content) {
  background-color: #11cdef;
  color: white;
}
:deep(.ie3 .wd-grid-item__content) {
  background-color: #2dce89;
  color: white;
}
:deep(.ie4 .wd-grid-item__content) {
  background-color: #fb6340;
  color: white;
}
</style>

<route type="home" lang="json">
  {
  "layout": "default",
  "name": "index",
  "style": {
  "navigationBarTitleText": "首页"
  }
  }
</route>
