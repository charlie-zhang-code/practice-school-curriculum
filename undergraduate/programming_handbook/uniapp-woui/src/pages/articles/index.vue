<script setup lang="ts">
  import { ref } from 'vue'

  import { request } from '@/utils'

  const artcles = ref()

  const page = ref<number>(1)
  const page_size = ref<number>(10)
  function handleChange({ value }) {
    getPageData()
  }

  const search = ref()
  async function getPageData() {
    const response = await request.Get(`/api/articles/`, {
      params: {
        search: search.value,
        page: page.value,
        page_size: page_size.value,
      },
    })

    artcles.value = response.data
  }

  async function cancel() {
    const response = await request.Get(`/api/articles/`, {
      params: {
        search: '',
        page: page.value,
        page_size: page_size.value,
      },
    })

    artcles.value = response.data
  }

  onMounted(() => {
    getPageData()
  })
</script>

<template>
  <view>
    <wd-search v-model="search" @search="getPageData" @cancel="cancel" />
    <wd-cell-group border>
      <wd-cell :title="item.title" :label="item.time_since_published" is-link v-for="item in artcles?.record"
        :key="item.id" :to="`/pages/article/index?id=${item.id}`" />
    </wd-cell-group>
    <wd-pagination v-model="page" :total="artcles?.total" :page-size="artcles?.size" @change="handleChange" show-icon
      show-message />
  </view>
</template>

<style scoped lang="scss">

</style>

<route lang="json">
  {
  "layout": "base",
  "name": "articles",
  "style": {
  "navigationBarTitleText": "更多"
  }
  }
</route>
