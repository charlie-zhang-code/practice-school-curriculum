<script setup lang="ts">
  import { ref } from 'vue'

  import { request } from '@/utils'

  const tab = ref<number>()
  const tabs = ref([])
  async function getTabsData() {
    const response = await request.Get('/api/commands/categories/')
    tabs.value = response.data
  }
  const tlist = ref([])
  const search = ref()
  const i = ref(1)
  async function getData() {
    const response = await request.Get('/api/commands/', {
      params: {
        search: search.value,
        category: i.value,
      },
    })
    tlist.value = response.data
  }

  onLoad(() => {
    getData()
    getTabsData()
  })

  // 搜索
  function cancel() {
    search.value = ''
    getData()
  }

  const activeTab = ref(0)

  function handleClick(e) {
    i.value = e.name
    getData()
  }

  // 复制命令函数
  function copyCommand(command : string) {
    uni.setClipboardData({
      data: command,
      success: () => {
        uni.showToast({
          title: '复制成功',
          icon: 'success'
        })
      },
      fail: () => {
        uni.showToast({
          title: '复制失败',
          icon: 'error'
        })
      }
    })
  }
</script>

<template>
  <view>
    <wd-search v-model="search" @search="getData" @cancel="cancel" />
    <wd-tabs @click="handleClick" auto-line-width animated slidable="always">
      <block v-for="item in tabs" :key="item?.id">
        <wd-tab :title="item?.name" :name="item?.id" style="background-color: #f8f9fa; padding-top: 24rpx;">
          <wd-card :title="ite?.title" v-for="ite in tlist" :key="ite?.id">
            <text >
              {{ite.description}}
            </text>
            <view class="command-content">
              <text class="command-text">{{ite.command}}</text>
            </view>
            <template #footer>
              <wd-button size="small" plain @click="copyCommand(ite.command)">复制</wd-button>
            </template>
          </wd-card>
        </wd-tab>
      </block>
    </wd-tabs>
  </view>
</template>

<style scoped lang="scss">
  .command-content {
    background-color: #f8f9fa;
    border-radius: 8rpx;
    padding: 20rpx;
    margin-top: 24rpx;
    font-family: 'Courier New', Courier, monospace;

    .command-text {
      font-size: 26rpx;
      color: #333;
      word-break: break-all;
    }
  }
</style>

<route lang="json">
  {
  "layout": "default",
  "name": "command",
  "style": {
  "navigationBarTitleText": "命令"
  }
  }
</route>
