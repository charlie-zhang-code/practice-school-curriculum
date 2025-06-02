<script setup lang="ts">
  import { ref } from 'vue'
  import { request } from '@/utils'
  const tabs = ref([])
  async function getTabsData() {
    const response = await request.Get('/api/snippets/categories/')
    tabs.value = response.data
  }

  const tlist = ref([])
  const searchs = ref()
  const i = ref(1)
  async function getCommandData() {
    const response = await request.Get('/api/snippets/', {
      params: {
        search: searchs.value,
        category: i.value,
      },
    })
    tlist.value = response.data
  }
  onLoad(() => {
    getCommandData()
    getTabsData()
  })

  function handleClick(e) {
    i.value = e.name
    getCommandData()
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
  // 搜索
  const value = ref<string>('')

  function cancel() {
    searchs.value = ''
    getCommandData()
  }

  // tab
  const tab = ref<number>(0)

  const languages = ref([
    { id: 1, name: 'JavaScript', icon: 'javascript', color: '#f7df1e' },
    { id: 2, name: 'TypeScript', icon: 'typescript', color: '#3178c6' },
    { id: 3, name: 'Python', icon: 'python', color: '#3776ab' },
    { id: 4, name: 'Java', icon: 'java', color: '#007396' },
    { id: 5, name: 'C++', icon: 'cplusplus', color: '#00599c' },
    { id: 6, name: 'Go', icon: 'golang', color: '#00add8' },
    { id: 7, name: 'PHP', icon: 'php', color: '#777bb4' },
    { id: 8, name: 'Swift', icon: 'swift', color: '#f05138' }
  ])
</script>

<template>
  <view>
    <wd-search v-model="searchs" @search="getCommandData" @cancel="cancel" />

    <wd-tabs @click="handleClick" auto-line-width animated slidable="always">
      <block v-for="item in tabs" :key="item?.id">
        <wd-tab :title="item?.name" :name="item?.id" style="background-color: #f8f9fa; padding-top: 24rpx;">
          <wd-card :title="ite?.title" v-for="ite in tlist" :key="ite?.id">
            <text>
              {{ite.description}}
            </text>
            <view class="snippet-code">
              <text class="code-text">{{ite.code}}</text>
            </view>
            <template #footer>
              <view class="flex items-center justify-between">
                <text class="snippet-language">{{ite.language}}</text>
                              <wd-button size="small" plain @click="copyCommand(ite.code)">复制</wd-button>
              </view>
            </template>
          </wd-card>
        </wd-tab>
      </block>
    </wd-tabs>

  </view>
</template>

<style scoped lang="scss">
  .snippet-code {
    margin-top: 24rpx;
    position: relative;
    background-color: #282c34;
    border-radius: 8rpx;
    padding: 30rpx;
    margin-bottom: 20rpx;
    font-family: 'Courier New', Courier, monospace;

    .code-text {
      font-size: 26rpx;
      color: #abb2bf;
      word-break: break-all;
    }
  }

  .snippet-language {
    font-size: 24rpx;
    color: #666;
    padding: 4rpx 12rpx;
    border-radius: 4rpx;
  }
</style>

<route lang="json">
  {
  "layout": "default",
  "name": "code",
  "style": {
  "navigationBarTitleText": "代码"
  }
  }
</route>
