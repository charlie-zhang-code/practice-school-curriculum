<script lang="ts" setup>
import { request } from '@/utils'

const dataRef = ref()
async function getTest() {
  const data = await request.Get('/system/api/v1/sys/notice/list')
  dataRef.value = data
}
</script>

<template>
  <view>
    <wd-cell-group border>
      <wd-cell title="getTest" icon="info-circle" is-link @click="getTest" />
      <view>
        dataRef: {{ JSON.stringify(dataRef, null, 2) }}
      </view>
    </wd-cell-group>
  </view>
</template>

<style scoped lang="scss">

</style>

<route lang="json">
  {
  "layout": "base",
  "name": "example",
  "style": {
  "navigationBarTitleText": "例子"
  }
  }
</route>
