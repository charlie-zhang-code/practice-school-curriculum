<script lang="ts" setup>
import { useRoute, useRouter } from 'uni-mini-router'

import { nextTick, onMounted } from 'vue'

import { onShow } from '@dcloudio/uni-app'
import { useTabbar } from '@/composables/useTabbar'

const router = useRouter()
const route = useRoute()
const { activeTabbar, getTabbarItemValue, setTabbarItemActive, tabbarList } = useTabbar()

function handleTabbarChange({ value }: { value: string }) {
  setTabbarItemActive(value)
  router.pushTab({ name: value })
}

onMounted(() => {
  nextTick(() => {
    if (route.name && route.name !== activeTabbar.value.name) {
      setTabbarItemActive(route.name)
    }
  })
})

onShow(() => {
  // #ifdef APP-PLUS
  uni.hideTabBar()
  // #endif
})

function handleBackHome() {
  uni.reLaunch({ url: '/pages/index/index' })
}
</script>

<template>
  <wd-config-provider custom-style="min-height: 100vh">
    <wd-navbar :title="activeTabbar.title" left-arrow safe-area-inset-top placeholder fixed @click-left="handleBackHome">
      <template #left>
        <wd-icon name="home" size="18" />
      </template>
    </wd-navbar>

    <slot />

    <wd-tabbar
      :model-value="activeTabbar.name" placeholder bordered safe-area-inset-bottom fixed
      @change="handleTabbarChange"
    >
      <wd-tabbar-item
        v-for="(item, index) in tabbarList" :key="index" :name="item.name"
        :value="getTabbarItemValue(item.name)" :title="item.title" :icon="item.icon"
      />
    </wd-tabbar>
    <wd-notify />
    <wd-toast />
    <wd-message-box />
  </wd-config-provider>
</template>
