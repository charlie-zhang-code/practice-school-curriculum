<script setup lang="ts">
  import { ref } from 'vue'
  import { request } from '@/utils'
  const collectionData = ref([])
  async function getToolsData() {
    const response = await request.Get('/api/tools/by_category/')
    collectionData.value = response.data
  }

  onLoad(() => {
    getToolsData()
  })

  const handleToolClick = (tool : any) => {
    uni.showToast({ title: `进入${tool.name}工具`, icon: 'none' })
  }
</script>

<template>
  <view>
    <wd-cell-group border :title="item?.category.name" v-for="item in collectionData" :key="item?.category.id">
      <wd-cell :title="ite.name" :icon="ite.icon" is-link v-for="ite in item.tools" :key="ite.id"
        :to="ite?.link_target" />
    </wd-cell-group>

  </view>
</template>

<style scoped lang="scss">

</style>

<route lang="json">
  {
  "layout": "default",
  "name": "tools",
  "style": {
  "navigationBarTitleText": "工具"
  }
  }
</route>
